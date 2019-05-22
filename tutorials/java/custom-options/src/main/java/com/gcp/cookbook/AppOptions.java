package com.gcp.cookbook;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface AppOptions extends PipelineOptions
{
    @Description("Minimum Value")
    @Default.Integer(5)
    Integer getMinimumValue();
    void setMinimumValue(Integer value);
}
