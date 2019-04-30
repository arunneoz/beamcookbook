package com.gcp.cookbook.beam.recipes.prebuiltxforms.joins;

import com.gcp.cookbook.beam.recipes.BeamFunctions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.extensions.joinlibrary.Join;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class OuterJoinBeamExtensions {

    private static final Logger LOG = LoggerFactory.getLogger(OuterJoinBeamExtensions.class);

    public static void main(String[] args) {

        inner_join_on_2_datasets_with_sdk_extension();
        output_2_pairs_for_1_to_2_relationship_join();

    }

    public static void inner_join_on_2_datasets_with_sdk_extension() {
        Pipeline pipeline = BeamFunctions.createPipeline("Inner join with the extension");
        List<KV<String, Integer>> ordersPerUser1 = Arrays.asList(
                KV.of("user1", 1000), KV.of("user2", 200), KV.of("user3", 100), KV.of("user6", 100)
        );
        List<KV<String, Integer>> ordersPerUser2 = Arrays.asList(
                KV.of("user1", 1100), KV.of("user2", 210), KV.of("user3", 110), KV.of("user7", 100)
        );

        PCollection<KV<String, Integer>> ordersPerUser1Dataset = pipeline.apply(Create.of(ordersPerUser1));
        PCollection<KV<String, Integer>> ordersPerUser2Dataset = pipeline.apply(Create.of(ordersPerUser2));

        PCollection<KV<String, KV<Integer, Integer>>> joinedDatasets = Join.innerJoin(ordersPerUser1Dataset, ordersPerUser2Dataset);
        PCollection<KV<String, Integer>> amountsPerUser = joinedDatasets.apply(ParDo.of(new AmountsCalculator()));

        // user6 and user7 are ignored because they're not included in both datasets

        amountsPerUser.apply(ParDo.of(new DoFn<KV<String,Integer>, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {


                LOG.info(c.element().getKey() + " " + c.element().getValue());

              }
            }));

       // PAssert.that(amountsPerUser).containsInAnyOrder(KV.of("user1", 2100), KV.of("user2", 410), KV.of("user3", 210));
        pipeline.run().waitUntilFinish();
    }



    public static  void output_2_pairs_for_1_to_2_relationship_join() {
        Pipeline pipeline = BeamFunctions.createPipeline("Extension inner join for 1:n relationship");
        List<KV<String, Integer>> ordersPerUser1 = Arrays.asList(
                KV.of("user1", 1000), KV.of("user2", 200), KV.of("user3", 100)
        );
        List<KV<String, Integer>> ordersPerUser2 = Arrays.asList(
                KV.of("user1", 1100), KV.of("user2", 210), KV.of("user3", 110), KV.of("user2", 300)
        );

        PCollection<KV<String, Integer>> ordersPerUser1Dataset = pipeline.apply(Create.of(ordersPerUser1));
        PCollection<KV<String, Integer>> ordersPerUser2Dataset = pipeline.apply(Create.of(ordersPerUser2));
        PCollection<KV<String, KV<Integer, Integer>>> joinedDatasets = Join.innerJoin(ordersPerUser1Dataset, ordersPerUser2Dataset);
        PCollection<KV<String, Integer>> amountsPerUser = joinedDatasets.apply(ParDo.of(new AmountsCalculator()));

        // Join extension gives a little bit less of flexibility than the custom join processing for the case of 1:n
        // joins. It doesn't allow to combine multiple values into a single output. Instead it returns every
        // combination of joined keys
        amountsPerUser.apply(ParDo.of(new DoFn<KV<String,Integer>, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {


                LOG.info(c.element().getKey() + " " + c.element().getValue());

            }
        }));

        pipeline.run().waitUntilFinish();
    }


    static class AmountsCalculator extends DoFn<KV<String, KV<Integer, Integer>>, KV<String, Integer>> {
        @ProcessElement
        public void processElement(ProcessContext processContext) {
            KV<String, KV<Integer, Integer>> element = processContext.element();
            int totalAmount = element.getValue().getKey() + element.getValue().getValue();
            processContext.output(KV.of(element.getKey(), totalAmount));
        }
    }
}
