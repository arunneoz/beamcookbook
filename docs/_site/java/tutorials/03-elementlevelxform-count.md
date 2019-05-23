[Home](../../index.md)
# Getting Started Tutorial

## Setup Project
CD into tutorial folders
```bash
cd tutorials/03-elementlevelxform-count
```

## Understand Code

### Custom options class
To begin, let's look at a customs Options class.

Open <walkthrough-editor-open-file 
filePath="/beamcookbook/tutorials/03-elementlevelxform-count/src/main/java/com/gcp/cookbook/AppOptions.java">src/main/java/com/gcp/cookbook/03-elementlevelxform-count/AppOptions.java</walkthrough-editor-open-file>


- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/03-elementlevelxform-count/src/main/java/com/gcp/cookbook/AppOptions.java"
startLine="9" startCharacterOffset="0" 
endLine="12" endCharacterOffset="80">line 10-13</walkthrough-editor-select-line>
A default getter and setter for runtime argument.
```(----countbyChoice=false)```



*Explain Pipeline*
Now let's look at the pipeline main method. 

Open <walkthrough-editor-open-file 
filePath="/beamcookbook/tutorials/03-elementlevelxform-count/src/main/java/com/gcp/cookbook/CountPipeline.java">src/main/java/com/gcp/cookbook/getting-started/CountPipeline.java</walkthrough-editor-open-file>

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/03-elementlevelxform-count/src/main/java/com/gcp/cookbook/CountPipeline.java"
startLine="40" startCharacterOffset="0"
endLine="43" endCharacterOffset="80">line 40:</walkthrough-editor-select-line>
Initializes the Pipeline with default options.

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/03-elementlevelxform-count/src/main/java/com/gcp/cookbook/CountPipeline.java"
startLine="45" startCharacterOffset="0"
endLine="45" endCharacterOffset="110">line 42:</walkthrough-editor-select-line>
Get the Count Choice option

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/03-elementlevelxform-count/src/main/java/com/gcp/cookbook/CountPipeline.java"
startLine="49" startCharacterOffset="0"
endLine="53" endCharacterOffset="110">line 49:</walkthrough-editor-select-line>
Populate the pipeline with a list of Integers and use the Count method to calculate the Count globally

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/03-elementlevelxform-count/src/main/java/com/gcp/cookbook/CountPipeline.java"
startLine="52" startCharacterOffset="0"
endLine="58" endCharacterOffset="80">line 46:</walkthrough-editor-select-line>
Output the count.

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/03-elementlevelxform-count/src/main/java/com/gcp/cookbook/CountPipeline.java"
startLine="65" startCharacterOffset="0"
endLine="70" endCharacterOffset="110">line 46:</walkthrough-editor-select-line>
Populate the pipeline with a list of Integers and use the Count method to calculate the Count per Element Category


- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/03-elementlevelxform-count/src/main/java/com/gcp/cookbook/CountPipeline.java"
startLine="74" startCharacterOffset="0"
endLine="80" endCharacterOffset="80">line 46:</walkthrough-editor-select-line>
Output the count.

## Run Pipeline

```bash
mvn compile exec:java -Dexec.mainClass=com.gcp.cookbook.CountPipeline \
     -Dexec.args="--countbyChoice=false --runner=DirectRunner"
```

    