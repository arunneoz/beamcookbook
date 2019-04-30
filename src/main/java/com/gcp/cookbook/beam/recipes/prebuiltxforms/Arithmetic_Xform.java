package com.gcp.cookbook.beam.recipes.prebuiltxforms;

import com.gcp.cookbook.beam.recipes.BeamFunctions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Arithmetic_Xform {
    private static final Logger LOG = LoggerFactory.getLogger(Arithmetic_Xform.class);

    public static void main(String[] args) {

        mean_value_of_numeric_values();
       // compute_mean_per_key_of_numeric_values();
       // get_2_first_values_per_key();
       // get_the_first_2_elements();
        get_the_last_2_elements();
    }



    public static void mean_value_of_numeric_values() {
        Pipeline pipeline = BeamFunctions.createPipeline("Mean value transformation");
        PCollection<Integer> numbersCollection = pipeline.apply(Create.of(Arrays.asList(1, 2, 3, 4, 5,
                6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)));

        PCollection<Double> meanValue = numbersCollection.apply(Mean.globally());

        meanValue.apply(ParDo.of(new DoFn<Double, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().toString());
            }


        }));

       // PAssert.that(meanValue).containsInAnyOrder(10.5d);
        pipeline.run().waitUntilFinish();
    }

    // same applies for Sum

    public static void compute_mean_per_key_of_numeric_values() {
        Pipeline pipeline = BeamFunctions.createPipeline("Mean value transformation");
        PCollection<KV<String, Integer>> customerOrders = pipeline.apply(Create.of(Arrays.asList(
                KV.of("C#1", 100), KV.of("C#2", 108), KV.of("C#3", 120), KV.of("C#1", 209), KV.of("C#1", 210),
                KV.of("C#1", 200), KV.of("C#2", 450))));

        PCollection<KV<String, Double>> meanAmountPerCustomer = customerOrders.apply(Mean.perKey());

        meanAmountPerCustomer.apply(ParDo.of(new DoFn<KV<String,Double>, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().getKey() + ":" + c.element().getValue());
            }


        }));



        pipeline.run().waitUntilFinish();
    }



    public static void get_the_first_2_elements() {
        Pipeline pipeline = BeamFunctions.createPipeline("Top 2 transformation");
        PCollection<Integer> numbersCollection = pipeline.apply(Create.of(IntStream.rangeClosed(1, 20)
                .boxed().collect(Collectors.toList())));

        PCollection<List<Integer>> top2Items = numbersCollection.apply(Top.largest(2));

        top2Items.apply(ParDo.of(new DoFn<List<Integer>, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                ListIterator e = c.element().listIterator();
                while (e.hasNext()) {
                    LOG.info(e.next().toString());
                }
            }


        }));
        pipeline.run().waitUntilFinish();
    }


    public static void get_the_last_2_elements() {
        Pipeline pipeline = BeamFunctions.createPipeline("Top 2 reversed transformation");
        PCollection<Integer> numbersCollection = pipeline.apply(Create.of(IntStream.rangeClosed(1, 20)
                .boxed().collect(Collectors.toList())));

        PCollection<List<Integer>> top2Items = numbersCollection.apply(Top.smallest(2));

        top2Items.apply(ParDo.of(new DoFn<List<Integer>, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                ListIterator e = c.element().listIterator();
                while (e.hasNext()) {
                    LOG.info(e.next().toString());
                }
            }


        }));
        pipeline.run().waitUntilFinish();
    }


    public static void get_2_first_values_per_key() {
        Pipeline pipeline = BeamFunctions.createPipeline("Top 2 per key transformation");
        PCollection<KV<String, Integer>> customerOrders = pipeline.apply(Create.of(Arrays.asList(
                KV.of("C#1", 100), KV.of("C#2", 108), KV.of("C#3", 120), KV.of("C#1", 209), KV.of("C#1", 210),
                KV.of("C#1", 200), KV.of("C#2", 450))));

        PCollection<KV<String, List<Integer>>> top2Orders = customerOrders.apply(Top.largestPerKey(2));

        top2Orders.apply(ParDo.of(new DoFn<KV<String,List<Integer>>, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().getKey() + ":" + c.element().getValue().get(1));
            }


        }));
        pipeline.run().waitUntilFinish();
    }



}



