package com.gcp.cookbook.beam.recipes.prebuiltxforms;

import com.gcp.cookbook.beam.recipes.BeamFunctions;
import com.google.common.collect.Iterables;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.transforms.join.CoGbkResult;
import org.apache.beam.sdk.transforms.join.CoGroupByKey;
import org.apache.beam.sdk.transforms.join.KeyedPCollectionTuple;
import org.apache.beam.sdk.values.*;
import org.joda.time.Instant;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;

public class Count_Xform {
    private static final Logger LOG = LoggerFactory.getLogger(Count_Xform.class);

    public static void main(String[] args) {
        count_globally_elements_in_collection();
        should_count_the_occurrences_per_element_in_key_value_collection();



    }


    public static void count_globally_elements_in_collection() {
        Pipeline pipeline = BeamFunctions.createPipeline("Count transformation");
        PCollection<Integer> numbersCollection = pipeline.apply(Create.of(Arrays.asList(1, 2, 3)));

        PCollection<Long> allItemsCount = numbersCollection.apply(Count.globally());
        allItemsCount.apply(ParDo.of(new DoFn<Long, Void>() {
            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().toString());
            }
        }));

        //PAssert.that(allItemsCount).containsInAnyOrder(3L);
        pipeline.run().waitUntilFinish();
    }


    public static void should_count_the_occurrences_per_element_in_key_value_collection() {

        Pipeline pipeline = BeamFunctions.createPipeline("Count per element transformation");
        PCollection<String> dataCollection = pipeline.apply(Create.of(Arrays.asList("787", "737", "777", "737", "737",
                "777", "737")));

        PCollection<KV<String, Long>> perElementCount = dataCollection.apply(Count.<String>perElement());

        perElementCount.apply(ParDo.of(new DoFn<KV<String,Long>, Void>() {
           // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().getKey() + ":" + c.element().getValue() );
            }


        }));

        pipeline.run().waitUntilFinish();
    }


    }



