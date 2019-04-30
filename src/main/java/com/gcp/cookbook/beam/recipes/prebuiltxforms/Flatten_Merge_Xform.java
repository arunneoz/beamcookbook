package com.gcp.cookbook.beam.recipes.prebuiltxforms;

import com.gcp.cookbook.beam.recipes.BeamFunctions;
import com.google.common.collect.Lists;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Flatten_Merge_Xform {
    private static final Logger LOG = LoggerFactory.getLogger(Flatten_Merge_Xform.class);

    public static void main(String[] args) {
        //flatten_numbers_from_list_pcollection();
        group_orders_by_customer();



    }


    public static void flatten_numbers_from_list_pcollection() {
        Pipeline pipeline = BeamFunctions.createPipeline("Flatten transformation");
        PCollection<Integer> numbers1 = pipeline.apply(Create.of(1, 2, 3, 4));
        PCollection<Integer> numbers2 = pipeline.apply(Create.of(10, 11, 12, 13));
        PCollection<Integer> numbers3 = pipeline.apply(Create.of(20, 21, 22, 23));
        PCollectionList<Integer> numbersList = PCollectionList.of(numbers1).and(numbers2).and(numbers3);

        PCollection<Integer> flattenNumbers = numbersList.apply(Flatten.pCollections());
        flattenNumbers.apply(ParDo.of(new DoFn<Integer, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().toString());
            }


        }));

        pipeline.run().waitUntilFinish();
    }


    public static void group_orders_by_customer() {
        Pipeline pipeline = BeamFunctions.createPipeline("Group by key transformation");
        PCollection<KV<String, Integer>> customerOrders = pipeline.apply(Create.of(Arrays.asList(
                KV.of("C#1", 100), KV.of("C#2", 108), KV.of("C#3", 120), KV.of("C#1", 209), KV.of("C#1", 210),
                KV.of("C#1", 200), KV.of("C#2", 450))));

        PCollection<KV<String, Iterable<Integer>>> groupedOrders = customerOrders.apply(GroupByKey.create());

        groupedOrders.apply(ParDo.of(new DoFn<KV<String,Iterable<Integer>>, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {


                    LOG.info(c.element().getKey() + " " + c.element().getValue().toString());

            }




        }));

        pipeline.run().waitUntilFinish();
    }







    }



