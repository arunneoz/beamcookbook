# Getting Started Tutorial

## Setup Project
CD into root folder
```bash
cd tutorials
```

## Execute Maven
```bash
mvn archetype:generate \
     -DarchetypeGroupId=org.apache.beam \
     -DarchetypeArtifactId=beam-sdks-java-maven-archetypes-starter \
     -DarchetypeVersion=2.12.0 \
     -Dversion="0.1" \
     -DgroupId=com.gcp.cookbook.beam.recipes \
     -DartifactId=getting-started \
     -Dpackage=com.gcp.cookbook.getting-started \
     -DinteractiveMode=false
```

## Understand Code
```bash
cd getting-started/
```
### Open 
```src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java```

<walkthrough-editor-open-file 
filePath="/beamcookbook/tutorials/getting-started/src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java" 
text="Click to Open StarterPipeline.java">open</walkthrough-editor-open-file>


### Explain Pipeline
To begin, let's look at the pipline main method. 
- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/getting-started/src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java"
startLine="49" startCharacterOffset="4" 
endLine="51" endCharacterOffset="52">line 50</walkthrough-editor-select-line>
Initializes the Pipeline
 
- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/getting-started/src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java"
startLine="52" startCharacterOffset="4" 
endLine="52" endCharacterOffset="80">line 53</walkthrough-editor-select-line>
Populate the pipeline with a list of words

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/getting-started/src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java"
startLine="53" startCharacterOffset="4" 
endLine="58" endCharacterOffset="80">line 54</walkthrough-editor-select-line>
Simple Function to upper case each word, one word at a time.
 
- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/getting-started/src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java"
startLine="59" startCharacterOffset="4" 
endLine="64" endCharacterOffset="80">line 60</walkthrough-editor-select-line>
Simple Function to output each word
 



## Run Pipeline

    