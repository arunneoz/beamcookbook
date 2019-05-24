<walkthrough-author name="Mike Nimer" tutorialName="Getting Started" repositoryUrl="https://arunneoz.github.io/beamcookbook"></walkthrough-author>

[Home](../../index.md)
# Getting Started Tutorial 


**Launch this in Cloud Shell**
```bash
cloudshell launch-tutorial docs/java/tutorials/getting_started.md
```

**Open Project Folder**
CD into tutorials folder
```bash
cd tutorials/java/
```

## Generate Project Code
For this we can use a Maven Archetype to generate a Starter Project with a simple pipeline ready to modify.

```bash
mvn archetype:generate \
     -DarchetypeGroupId=org.apache.beam \
     -DarchetypeArtifactId=beam-sdks-java-maven-archetypes-starter \
     -DarchetypeVersion=2.12.0 \
     -Dversion="0.1" \
     -DgroupId=com.gcp.cookbook.beam \
     -DartifactId=getting-started \
     -Dpackage=com.gcp.cookbook \
     -DinteractiveMode=false
```

```bash
cd getting-started/
```

## Code Walkthrough

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
```
    Pipeline p = Pipeline.create(
    PipelineOptionsFactory.fromArgs(args).withValidation().create());
```
 
- <walkthrough-editor-select-line
filePath="/beamcookbook/java/tutorials/getting-started/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="52" startCharacterOffset="4" 
endLine="52" endCharacterOffset="80">line 53</walkthrough-editor-select-line>
Populate the pipeline with a list of words
```
p.apply(Create.of("Hello", "World"))
```

- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/java/getting-started/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="53" startCharacterOffset="4" 
endLine="58" endCharacterOffset="80">line 54</walkthrough-editor-select-line>
Simple Function to upper case each word, one word at a time.
```
    .apply(MapElements.via(new SimpleFunction<String, String>() {
      @Override
      public String apply(String input) {
        return input.toUpperCase();
      }
    }))
```


- <walkthrough-editor-select-line
filePath="/beamcookbook/tutorials/java/getting-started/src/main/java/com/gcp/cookbook/StarterPipeline.java"
startLine="59" startCharacterOffset="4" 
endLine="64" endCharacterOffset="80">line 60</walkthrough-editor-select-line>
Simple Function to output each word
```
    .apply(ParDo.of(new DoFn<String, Void>() {
      @ProcessElement
      public void processElement(ProcessContext c)  {
        LOG.info(c.element());
      }
    }));
```



## Run Pipeline
**Run Locally**
```bash
mvn compile exec:java \
    -Dexec.mainClass=com.gcp.cookbook.StarterPipeline \
    -Dexec.args="--runner=DirectRunner"
```
    