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

public class Filter_Xform {
    private static final Logger LOG = LoggerFactory.getLogger(Filter_Xform.class);

    public static void main(String[] args) {

        should_filter_null_values();
        filter_matched_string();
        filter_numeric_operators();



    }

    enum Filters implements SerializableFunction<String, Boolean> {
        NOT_EMPTY {
            @Override
            public Boolean apply(String input) {
                return !input.isEmpty();
            }
        }
    }


    public static void should_filter_null_values() {
        Pipeline pipeline = BeamFunctions.createPipeline("Filter Null Value ");
        PCollection<String> dataCollection = pipeline.apply(Create.of(Arrays.asList("", "a", "", "", "ab",
                "ab", "abc")));

        PCollection<String> notnullValues =
                dataCollection.apply(Filter.by(Filters.NOT_EMPTY));

        notnullValues.apply(ParDo.of(new DoFn<String, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().toString());
            }


        }));


        pipeline.run().waitUntilFinish();
    }


    public static void filter_matched_string()  {

        Pipeline pipeline = BeamFunctions.createPipeline("Match Strings Filter");
        PCollection<String> dataCollection = pipeline.apply(Create.of(Arrays.asList("737", "787", "777", "737", "777", "737")));

        PCollection<String> matchedString = dataCollection.apply(Filter.equal("737"));

        matchedString.apply(ParDo.of(new DoFn<String, Void>() {

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().toString());
            }

        }));

        pipeline.run().waitUntilFinish();
    }


    public static void filter_numeric_operators() {
        Pipeline pipeline = BeamFunctions.createPipeline("Numbers greater or equal to X filter");
        PCollection<Integer> dataCollection = pipeline.apply(Create.of(Arrays.asList(1, 2, 3)));

        PCollection<Integer> numbersGreaterOrEqual2 = dataCollection.apply(Filter.greaterThanEq(2));

        numbersGreaterOrEqual2.apply(ParDo.of(new DoFn<Integer, Void>() {

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().toString());
            }

        }));

        pipeline.run().waitUntilFinish();
    }


    }



