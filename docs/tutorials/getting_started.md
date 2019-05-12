# Getting Started

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
     -Dpackage=com.gcp.cookbook.beam.recipes.tutorials.getting-started \
     -DinteractiveMode=false
```

## Understand Code
- Open ```GettingStarted.java```

## Run Pipeline

