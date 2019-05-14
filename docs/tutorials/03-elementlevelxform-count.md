# Getting Started Tutorial

## Setup Project
CD into tutorial folders
```bash
cd tutorials/custom-options
```

## Understand Code

### Custom options class
To begin, let's look at a customs Options class.

Open <walkthrough-editor-open-file 
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/AppOptions.java">src/main/java/com/gcp/cookbook/custom-options/AppOptions.java</walkthrough-editor-open-file>


- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/AppOptions.java"
startLine="9" startCharacterOffset="0" 
endLine="12" endCharacterOffset="80">line 10-13</walkthrough-editor-select-line>
A default getter and setter for runtime argument.
```(--filterPattern=5)```



*Explain Pipeline*
Now let's look at the pipeline main method. 

Open <walkthrough-editor-open-file 
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java">src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java</walkthrough-editor-open-file>

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="38" startCharacterOffset="0" 
endLine="39" endCharacterOffset="80">line 50:</walkthrough-editor-select-line>
Initializes the Pipeline with default options.

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="41" startCharacterOffset="0" 
endLine="41" endCharacterOffset="80">line 42:</walkthrough-editor-select-line>
Populate the pipeline with a list of Integers.

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="43" startCharacterOffset="0" 
endLine="43" endCharacterOffset="80">line 44:</walkthrough-editor-select-line>
Filter out all numbers smaller then the filterPattern.

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/custom-options/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="45" startCharacterOffset="0" 
endLine="50" endCharacterOffset="80">line 46:</walkthrough-editor-select-line>
Output the integers that passed the filter.

## Run Pipeline

    