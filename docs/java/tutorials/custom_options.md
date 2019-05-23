[Home](../../index.md)
# Getting Started Tutorial

Often with DataFlow you will need to define custom inputs when you run the workflow. This example will define a custom option class that we will use to store the runtime variables we need while defining our pipeline. 

**Launch this in Cloud Shell**
```bash
cloudshell launch-tutorial docs/java/tutorials/custom_options.md
```

**Open Project Folder**
CD into tutorial folders
```bash
cd tutorials/java/custom-options
```


## Code Walkthrough

**AppOptions Class**

This class has one runtime value, with a default. When you invoke the pipeline you will use this argument
```
--minimumValue=5
```
Open <walkthrough-editor-open-file filePath="/beamcookbook/tutorials/java/custom-options/src/main/java/com/gcp/cookbook/AppOptions.java">AppOptions.java</walkthrough-editor-open-file>
<br/>

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/java/custom-options/src/main/java/com/gcp/cookbook/AppOptions.java"
startLine="9" startCharacterOffset="0" 
endLine="9" endCharacterOffset="80">line 9</walkthrough-editor-select-line>
Description of the property
``` 
@Description("Minimum Value") 
```
<br/>

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/java/custom-options/src/main/java/com/gcp/cookbook/AppOptions.java"
startLine="10" startCharacterOffset="0" 
endLine="10" endCharacterOffset="80">line 10</walkthrough-editor-select-line>
Default value, if this is not set the property is required.
``` 
@Default.Integer(5) 
```
<br/>

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/java/custom-options/src/main/java/com/gcp/cookbook/AppOptions.java"
startLine="11" startCharacterOffset="0" 
endLine="12" endCharacterOffset="80">line 11-12</walkthrough-editor-select-line>
Getter and Setter methods for the property. 
```
Integer getMinimumValue();
void setMinimumValue(Integer value);
```
<br/>


**Pipeline Class**

This class defines the Apache Beam pipeline DAG, it also contains the Java Main method. 

Open <walkthrough-editor-open-file filePath="/beamcookbook/tutorials/java/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java">StarterPipeline.java</walkthrough-editor-open-file>

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/java/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="38" startCharacterOffset="0" 
endLine="39" endCharacterOffset="80">line 50:</walkthrough-editor-select-line>
Initializes the Pipeline with our custom AppOptions class. Now we can use the getter methods as needed.
```
AppOptions appOptions = PipelineOptionsFactory.fromArgs(args).as(com.gcp.cookbook.AppOptions.class);
Pipeline p = Pipeline.create(appOptions);
```
<br/>

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/java/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="41" startCharacterOffset="0" 
endLine="41" endCharacterOffset="80">line 42:</walkthrough-editor-select-line>
Populate the pipeline with a list of Integers, so we have some messages to send through the pipeline.
```
p.apply(Create.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
```
<br/>


- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/java/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="43" startCharacterOffset="0" 
endLine="43" endCharacterOffset="80">line 44:</walkthrough-editor-select-line>
Filter out all numbers smaller then the filterPattern.
```
.apply(Filter.greaterThan(appOptions.getMinimumValue()))
```
<br/>


- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/java/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="45" startCharacterOffset="0" 
endLine="50" endCharacterOffset="80">line 46:</walkthrough-editor-select-line>
Log the integers that passed the filter.
```
.apply(ParDo.of(new DoFn<Integer, Integer>() {
    @ProcessElement
    public void processElement(ProcessContext c) {
        LOG.info(c.element().toString());
        //return the value
        c.output(c.element());
    }
}));
```
<br/>


## Run Pipeline

*Run Locally*
```bash
mvn compile exec:java \
    -Dexec.mainClass=com.gcp.cookbook.StarterPipeline \
    -Dexec.args="--runner=DirectRunner"
```

*Run with DataFlow*
```bash
    export project=<project id>
```
```bash
mvn compile exec:java \
    -Dexec.mainClass=com.gcp.cookbook.StarterPipeline \
    -Dexec.args="--runner=DataFlowRunner --project=$project"
```

*Deploy Template*
```bash
    export project=<project id>
```
```bash
mvn compile exec:java \
    -Dexec.mainClass=com.gcp.cookbook.StarterPipeline \
    -Dexec.args="--runner=DataFlowRunner --stagingLocation=gs://<gcs bucket>/staging"
```

    