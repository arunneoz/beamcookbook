package com.gcp.cookbook.beam.recipes.prebuiltxforms.joins;

import com.gcp.cookbook.beam.recipes.prebuiltxforms.Arithmetic_Xform;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.View;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.gcp.cookbook.beam.recipes.BeamFunctions;

public class BroadCastJoin {

    private static final Logger LOG = LoggerFactory.getLogger(BroadCastJoin.class);

    public static void main(String[] args) {

        two_datasets_with_side_inputs_using_broadcastjoin();


    }

    public static void two_datasets_with_side_inputs_using_broadcastjoin() {
        Pipeline pipeline = BeamFunctions.createPipeline("Broadcast join with side input");
        List<KV<String, String>> ordersWithCountry = Arrays.asList(
                KV.of("order_1", "fr"), KV.of("order_2", "fr"), KV.of("order_3", "pl")
        );
        List<KV<String, String>> countriesWithIsoCode = Arrays.asList(
                KV.of("fr", "France"), KV.of("pl", "Poland"), KV.of("de", "Germany")
        );

        PCollection<KV<String, String>> ordersWithCountriesDataset = pipeline.apply(Create.of(ordersWithCountry));
        PCollection<KV<String, String>> countriesMapDataset = pipeline.apply(Create.of(countriesWithIsoCode));
        PCollectionView<Map<String, String>> countriesSideInput = countriesMapDataset.apply(View.asMap());
        PCollection<String> ordersSummaries = ordersWithCountriesDataset.apply(ParDo.of(new DoFn<KV<String, String>, String>() {
            @ProcessElement
            public void processElement(ProcessContext context) {
                Map<String, String> countriesByIso = context.sideInput(countriesSideInput);
                KV<String, String> processedElement = context.element();
                String orderCountry = countriesByIso.get(processedElement.getValue());
                String orderSummary = processedElement.getKey() + " (" + orderCountry + ")";
                context.output(orderSummary);
            }
        }).withSideInputs(countriesSideInput));

        ordersSummaries.apply(ParDo.of(new DoFn<String, Void>() {
            // @Override

            @ProcessElement
            public void processElement(ProcessContext c)  {
                LOG.info(c.element().toString());
            }


        }));
        pipeline.run().waitUntilFinish();
    }
}
