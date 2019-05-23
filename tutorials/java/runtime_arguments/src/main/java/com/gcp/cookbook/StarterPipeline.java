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

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.ParDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example for writing a pipeline with a custom runtime option and a filter method
 *
 * <p>The example takes a filter and outputs all numbers greater then the filter
 **/
public class StarterPipeline {
    private static final Logger LOG = LoggerFactory.getLogger(StarterPipeline.class);

    public static void main(String[] args) {
        AppOptions appOptions = PipelineOptionsFactory.fromArgs(args).as(com.gcp.cookbook.AppOptions.class);
        Pipeline p = Pipeline.create(appOptions);

        p.apply(Create.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))

            .apply(Filter.greaterThan(appOptions.getMinimumValue()))

            .apply(ParDo.of(new DoFn<Integer, Integer>() {
                @ProcessElement
                public void processElement(ProcessContext c) {
                    LOG.info(c.element().toString());
                    //return the value
                    c.output(c.element());
                }
            }));

        p.run();
    }
}
