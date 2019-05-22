package com.gcp.cookbook;

import org.apache.beam.sdk.extensions.gcp.options.GcpOptions;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.ValueProvider;

public interface AppOptions extends GcpOptions
{
    @Description("Element by Count ")
    @Default.Boolean(true)
    ValueProvider<Boolean> getCountbyChoice();
    void setCountbyChoice(ValueProvider<Boolean> value);
}
