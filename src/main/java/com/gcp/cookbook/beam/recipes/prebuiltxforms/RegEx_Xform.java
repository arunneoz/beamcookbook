package com.gcp.cookbook.beam.recipes.prebuiltxforms;

import com.gcp.cookbook.beam.recipes.BeamFunctions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class RegEx_Xform {
    private static final Logger LOG = LoggerFactory.getLogger(RegEx_Xform.class);

    public static void main(String[] args) {

        replace_all_a_by_X();
        replace_first_match();
        split_sentences_by_whitespace();
        extract_strings_text();


    }



    public static void replace_all_a_by_X() {
        Pipeline pipeline = BeamFunctions.createPipeline("RegEx replaceAll transformation");
        PCollection<String> dataCollection = pipeline.apply(Create.of(Arrays.asList("aa", "aba", "baba")));

        PCollection<String> replacedWords = dataCollection.apply(Regex.replaceAll("a", "1"));

        replacedWords.apply(ParDo.of(new DoFn<String, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().toString());
            }


        }));

        pipeline.run().waitUntilFinish();
    }


    public static void replace_first_match() {
        Pipeline pipeline = BeamFunctions.createPipeline("RegEx replaceAll transformation");
        PCollection<String> dataCollection = pipeline.apply(Create.of(Arrays.asList("aa", "aaaa", "aba")));

        PCollection<String> replacedWords = dataCollection.apply(Regex.replaceFirst("a", "1"));

        replacedWords.apply(ParDo.of(new DoFn<String, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().toString());
            }


        }));
        pipeline.run().waitUntilFinish();
    }



    public static void split_sentences_by_whitespace() {
        Pipeline pipeline = BeamFunctions.createPipeline("RegEx replaceAll transformation");
        PCollection<String> dataCollection = pipeline.apply(Create.of(Arrays.asList("aa bb cc", "aaaa aa cc", "aba bab")));

        PCollection<String> splittedWords = dataCollection.apply(Regex.split("\\s"));

        splittedWords.apply(ParDo.of(new DoFn<String, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().toString());
            }


        }));
        pipeline.run().waitUntilFinish();
    }


    public static void extract_strings_text() {
        Pipeline pipeline = BeamFunctions.createPipeline("RegEx extract string transformation");
        PCollection<String> dataCollection = pipeline.apply(Create.of(Arrays.asList("4905 Lakeway Drive, College Station, Texas 77845 USA", "673 Jasmine Street, Los Angeles, CA 90024-4554", "2376 First street, San Diego, CA 90126")));

        PCollection<String> extractedWords = dataCollection.apply(Regex.find("\\d{5}(?:[-\\s]\\d{4})?"));

        extractedWords.apply(ParDo.of(new DoFn<String, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().toString());
            }


        }));
        pipeline.run().waitUntilFinish();
    }



    }



