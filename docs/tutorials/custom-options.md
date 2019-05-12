# Getting Started Tutorial

## Setup Project
CD into tutorial folders
```bash
cd tutorials/custom-options
```

## Understand Code

### Open 
<walkthrough-editor-open-file 
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java">src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java</walkthrough-editor-open-file>


### Explain Pipeline
To begin, let's look at the pipline main method. 

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/getting-started/src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java"
startLine="49" startCharacterOffset="4" 
endLine="51" endCharacterOffset="52">line 50</walkthrough-editor-select-line>
Initializes the Pipeline with default options

We are going to change this to support runtime arguments


### Create options class
```bash
cd src/main/java/com/gcp/cookbook/
```

*Create File "AppOptions.java"*
```
package main.java.com.gcp.cookbook;

import org.apache.beam.sdk.extensions.gcp.options.GcpOptions;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.ValueProvider;

public interface AppOptions extends GcpOptions
{
    @Description("Filter Pattern")
    @Default.String("World")
    ValueProvider<String> getFilterPattern();
    void setFilterPattern(ValueProvider<String> value);
}
```

Replace <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/getting-started/src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java"
startLine="49" startCharacterOffset="4" 
endLine="51" endCharacterOffset="52">line 50 & 51</walkthrough-editor-select-line> with this line
```
Pipeline p = Pipeline.create(PipelineOptionsFactory.fromArgs(args).as(AppOptions.class));
```


## Run Pipeline

    