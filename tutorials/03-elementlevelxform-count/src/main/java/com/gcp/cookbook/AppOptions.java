package main.java.com.gcp.cookbook;

import org.apache.beam.sdk.extensions.gcp.options.GcpOptions;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.ValueProvider;

public interface AppOptions extends GcpOptions
{
    @Description("Filter Pattern")
    @Default.Integer(5)
    ValueProvider<Integer> getFilterPattern();
    void setFilterPattern(ValueProvider<Integer> value);
}
