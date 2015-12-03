[libSBOLj](https://github.com/SynBioDex/libSBOLj) provides the core Java interfaces and their implementation for 
the [Synthetic Biology Open Language (SBOL)](http://www.sbolstandard.org/sbolstandard/specification). The library provides an API to 
work with SBOL objects, the functionality to read and write SBOL documents as XML/RDF files, and a validator to check the 
correctness of SBOL models. 

# Using the libSBOLj library

## In a Maven project:

In a Maven project that utilizes the libSBOLj library, add a dependency in the Maven project's ```pom.xml``` file.

```
<dependency>
	<groupId>org.sbolstandard</groupId>
	<artifactId>libSBOLj</artifactId>
	<version>2.0.0</version>
</dependency>
```
 
## In a non-Maven project:


Download ```libSBOLj-<version>-withDependencies.jar``` from the latest github release.

## Running in the command line:

libSBOLj comes with a command-line interface (CLI) that can be used to validate SBOL files. You can execute 
```libSBOLj-<version>-withDependencies.jar``` to validate and convert files as follows.

```
java -jar libSBOLj-<version>-withDependencies.jar <inputFile>
```
    
If validation is successful, the program will print the contents of the SBOL document. You can also output the result to a file. 

```
java -jar libSBOLj-<version>-withDependencies.jar <inputFile> -o <outputFile>
```

If validation fails with an error, there will be a message printed about the validation error.  In addition to checking all required validation rules, it will also check if the URIs are compliant and whether the SBOL document is complete (i.e., all referenced objects are contained within the file).  These validation checks can be turned off with the -n and -i flags, respectively.

If the input file is an SBOL 1.1 file, then it will convert the file into an SBOL 2.0 file.  This conversion should be provided a default URI prefix.  It can also be provided a default version, if desired.  Finally, the -t flag will insert the type of top level objects into the URI during conversion, if desired.

```
java -jar libSBOLj-<version>-withDependencies.jar <inFile> -o <outFile> -p <URIprefix> -v <version>
```

## Using the latest libSBOLj SNAPSHOT

### Getting the libSBOLj source

1. [Create](https://github.com/) a GitHub account.
2. [Setup](https://help.github.com/articles/set-up-git) Git on your machine.
3. [Clone](https://help.github.com/articles/cloning-a-repository/) the libSBOLj GitHub repository to your machine.

### Compiling and Packaging libSBOLj 

1. [Setup](http://maven.apache.org/download.cgi) Apache Maven. A tutorial on using Apache Maven is provided [here](http://maven.apache.org/guides/getting-started/index.html).

2. In the command line, change to the libSBOLj directory (e.g. ```cd /path/to/libSBOLj```) and execute the following command

```
mvn package
```

This will compile the libSBOLj source files, package the compiled source into a libSBOLj JAR file (```libSBOLj-<version>-SNAPSHOT-withDependencies.jar```), and place the JAR file into the ```core2/target``` sub-directory. 

