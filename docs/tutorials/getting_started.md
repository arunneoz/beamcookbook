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
- Open ```src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java```

<walkthrough-editor-open-file filePath="./src/main/java/com/gcp/cookbook/getting-started/StarterPipeline.java" text="Open StarterPipeline.java"></walkthrough-editor-open-file>


## Run Pipeline

