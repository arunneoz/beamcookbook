(Home)[../index.md]

# Getting Started Tutorial

## Setup Project
CD into tutorials folder
```bash
cd tutorials/java/
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
     -Dpackage=com.gcp.cookbook \
     -DinteractiveMode=false
```

## Understand Code
```bash
cd getting-started/
```
### Open 
<walkthrough-editor-open-file 
filePath="/beamcookbook/tutorials/java/getting-started/src/main/java/com/gcp/cookbook/StarterPipeline.java">StarterPipeline.java</walkthrough-editor-open-file>


### Explain Pipeline
To begin, let's look at the pipline main method. 
- <walkthrough-editor-select-line
filePath="/beamcookbook/java/tutorials/getting-started/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="49" startCharacterOffset="4" 
endLine="51" endCharacterOffset="52">line 50</walkthrough-editor-select-line>
Initializes the Pipeline
 
- <walkthrough-editor-select-line
filePath="/beamcookbook/java/tutorials/getting-started/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="52" startCharacterOffset="4" 
endLine="52" endCharacterOffset="80">line 53</walkthrough-editor-select-line>
Populate the pipeline with a list of words

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/java/getting-started/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="53" startCharacterOffset="4" 
endLine="58" endCharacterOffset="80">line 54</walkthrough-editor-select-line>
Simple Function to upper case each word, one word at a time.
 
- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/java/getting-started/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="59" startCharacterOffset="4" 
endLine="64" endCharacterOffset="80">line 60</walkthrough-editor-select-line>
Simple Function to output each word
 



## Run Pipeline
**Run Locally**
```bash
mvn compile exec:java \
    -Dexec.mainClass=com.gcp.cookbook.StarterPipeline \
    -Dexec.args="--runner=DirectRunner"
```
    