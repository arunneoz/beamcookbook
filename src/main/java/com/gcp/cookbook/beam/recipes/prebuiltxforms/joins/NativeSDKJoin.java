package com.gcp.cookbook.beam.recipes.prebuiltxforms.joins;

import com.gcp.cookbook.beam.recipes.BeamFunctions;
import com.google.common.collect.Iterables;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.join.CoGbkResult;
import org.apache.beam.sdk.transforms.join.CoGroupByKey;
import org.apache.beam.sdk.transforms.join.KeyedPCollectionTuple;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TupleTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class NativeSDKJoin {

    private static final Logger LOG = LoggerFactory.getLogger(NativeSDKJoin.class);

    public static void main(String[] args) {

        join_2_datasets_which_all_have_1_matching_key_with_native_sdk_code();


    }

    public static void join_2_datasets_which_all_have_1_matching_key_with_native_sdk_code() {
        Pipeline pipeline = BeamFunctions.createPipeline("1:n join with native SDK");
        List<KV<String, Integer>> ordersPerUser1 = Arrays.asList(
                KV.of("user1", 1000), KV.of("user2", 200), KV.of("user3", 100)
        );
        List<KV<String, Integer>> ordersPerUser2 = Arrays.asList(
                KV.of("user1", 1100), KV.of("user2", 210), KV.of("user3", 110),
                KV.of("user1", 1200), KV.of("user2", 220), KV.of("user3", 120)
        );

        PCollection<KV<String, Integer>> ordersPerUser1Dataset = pipeline.apply(Create.of(ordersPerUser1));
        PCollection<KV<String, Integer>> ordersPerUser2Dataset = pipeline.apply(Create.of(ordersPerUser2));

        final TupleTag<Integer> amountTagDataset1 = new TupleTag<>();
        final TupleTag<Integer> amountTagDataset2 = new TupleTag<>();
        PCollection<KV<String, CoGbkResult>> groupedCollection = KeyedPCollectionTuple
                .of(amountTagDataset1, ordersPerUser1Dataset)
                .and(amountTagDataset2, ordersPerUser2Dataset)
                .apply(CoGroupByKey.create());

        PCollection<KV<String, Integer>> totalAmountsPerUser = groupedCollection.apply(ParDo.of(new DoFn<KV<String, CoGbkResult>, KV<String, Integer>>() {
            @ProcessElement
            public void processElement(ProcessContext processContext) {
                KV<String, CoGbkResult> element = processContext.element();
                Iterable<Integer> dataset1Amounts = element.getValue().getAll(amountTagDataset1);
                Iterable<Integer> dataset2Amounts = element.getValue().getAll(amountTagDataset2);
                Integer sumAmount = StreamSupport.stream(Iterables.concat(dataset1Amounts, dataset2Amounts).spliterator(), false)
                        .collect(Collectors.summingInt(n -> n));
                processContext.output(KV.of(element.getKey(), sumAmount));
            }
        }));


        totalAmountsPerUser.apply(ParDo.of(new DoFn<KV<String,Integer>, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {


                LOG.info(c.element().getKey() + " " + c.element().getValue());

            }




        }));

        pipeline.run().waitUntilFinish();
    }
}
