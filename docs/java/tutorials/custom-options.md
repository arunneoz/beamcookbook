# Getting Started Tutorial
Often with DataFlow you will need to define custom inputs when you run the workflow. This example will define a custom option class that we will use to store the runtime variables we need while defining our pipeline. 


## Setup Project
CD into tutorial folders
```bash
cd tutorials/custom-options
```


## Understand The Code

*AppOptions Class*

Open <walkthrough-editor-open-file 
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/AppOptions.java">src/main/java/com/gcp/cookbook/custom-options/AppOptions.java</walkthrough-editor-open-file>


- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/AppOptions.java"
startLine="9" startCharacterOffset="0" 
endLine="12" endCharacterOffset="80">line 10-13</walkthrough-editor-select-line>
A default getter and setter for runtime argument.
```(--filterPattern=5)```



*Pipeline Class*
Now let's look at the pipeline main method. 

Open <walkthrough-editor-open-file 
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java">src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java</walkthrough-editor-open-file>

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="38" startCharacterOffset="0" 
endLine="39" endCharacterOffset="80">line 50:</walkthrough-editor-select-line>
Initializes the Pipeline with our custom AppOptions class.

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="41" startCharacterOffset="0" 
endLine="41" endCharacterOffset="80">line 42:</walkthrough-editor-select-line>
Populate the pipeline with a list of Integers, so we have some messages to send through the pipeline.

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="43" startCharacterOffset="0" 
endLine="43" endCharacterOffset="80">line 44:</walkthrough-editor-select-line>
Filter out all numbers smaller then the filterPattern.

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="45" startCharacterOffset="0" 
endLine="50" endCharacterOffset="80">line 46:</walkthrough-editor-select-line>
Log the integers that passed the filter.

## Run Pipeline

*Run Locally*
```bash
mvn compile exec:java \
    -Dexec.mainClass=com.gcp.cookbook.CountPipeline \
    -Dexec.args="--runner=DirectRunner"
```

*Run with DataFlow*
```bash
mvn compile exec:java \
    -Dexec.mainClass=com.gcp.cookbook.CountPipeline \
    -Dexec.args="--runner=DataFlowRunner"
```

*Deploy Template*
```bash
mvn compile exec:java \
    -Dexec.mainClass=com.gcp.cookbook.CountPipeline \
    -Dexec.args="--runner=DataFlowRunner --stagingLocation=gs://<gcs bucket>/staging"
```

    