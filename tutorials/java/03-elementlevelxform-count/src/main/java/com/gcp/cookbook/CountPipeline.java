/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gcp.cookbook;

import com.gcp.cookbook.AppOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


/**
 * An example for writing a pipeline with a custom runtime option and a count method
 *
 * <p>The example shows you how to perform arthimatic operation Count on a dataset
 **/
public class CountPipeline {
    private static final Logger LOG = LoggerFactory.getLogger(CountPipeline.class);

    public static void main(String[] args) {
        AppOptions appOptions = PipelineOptionsFactory.fromArgs(args).withValidation().as(AppOptions.class);
        Pipeline pipeline = Pipeline.create(appOptions);

        // This is an example of doing Count operation on a entire Dataset

        if(appOptions.getCountbyChoice().get())
        {
            PCollection<Integer> numbersCollection = pipeline.apply(Create.of(Arrays.asList(1, 2, 3)));

            PCollection<Long> allItemsCount = numbersCollection.apply(Count.globally());
            allItemsCount.apply(ParDo.of(new DoFn<Long, Void>() {
                @ProcessElement
                public void processElement(ProcessContext c) {
                    LOG.info(c.element().toString());
                }
            }));

        }

        else {

            // This is an example of doing Count operation per element Category

            PCollection<String> dataCollection = pipeline.apply(Create.of(Arrays.asList("787", "737", "777", "737", "737", "777", "737")));

            PCollection<KV<String, Long>> perElementCount = dataCollection.apply(Count.<String>perElement());

            perElementCount.apply(ParDo.of(new DoFn<KV<String, Long>, Void>() {
                // @Override
                @ProcessElement
                public void processElement(ProcessContext c) {
                    LOG.info(c.element().getKey() + ":" + c.element().getValue());
                }
            }));
        }

        //PAssert.that(allItemsCount).containsInAnyOrder(3L);
        pipeline.run();
    }
}
