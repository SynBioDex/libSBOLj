# libSBOLj: SBOL Java library

[libSBOLj](https://github.com/SynBioDex/libSBOLj) provides the core Java interfaces and their implementation for 
the [Synthetic Biology Open Language (SBOL)](http://www.sbolstandard.org/sbolstandard/specification). The library provides an API to 
work with SBOL objects, the functionality to read and write SBOL documents as XML/RDF files, and a validator to check the 
correctness of SBOL models. 

## Getting the libSBOLj source

1. Create a GitHub account. [link](https://github.com/)
2. Download and set up Git. [link](https://help.github.com/articles/set-up-git)
3. Fork the libSBOLj repository and clone it to your machine. [link](https://help.github.com/articles/fork-a-repo)
4. Download and set up Maven. [link](http://maven.apache.org/download.cgi)
5. Change to your libSBOLj directory via the command line and execute the following command:

    mvn package

This will create the libSBOLj JAR file (libSBOLj-core2-2.0.0-SNAPSHOT-withDependencies.jar) and place it into the core2/target subdirectory. [link](http://maven.apache.org/guides/getting-started/index.html)

## Using libSBOLj


### libSBOLj command line

libSBOLj comes with a command-line interface (CLI) that can be used to validate SBOL files. After you build the 
libSBOLj-core2-2.0.0-SNAPSHOT-withDependencies.jar as described above, you can use it to validate files as follows after changing to the core2/target subdirectory:

    cd core2/target/
    java -jar libSBOLj-core2-2.0.0-SNAPSHOT-withDependencies.jar <inputFile>
    
If validation is successful, the program will print the contents of the SBOL document. You can also output the result to a file. 

    java -jar libSBOLj-core2-2.0.0-SNAPSHOT-withDependencies.jar <inputFile> -o <outputFile>

If validation fails with an error, there will be a message printed about the validation error.  In addition to checking all required validation rules, it will also check if the URIs are compliant and whether the SBOL document is complete (i.e., all referenced objects are contained within the file).  These validation checks can be turned off with the -n and -i flags, respectively.

If the input file is an SBOL 1.1 file, then it will convert the file into an SBOL 2.0 file.  This conversion should be provided a default URI prefix with the -p flag.  It can also be provided a default version, if desired.  Finally, the -t flag will insert the type of top level objects into the URI during conversion, if desired.

    java -jar libSBOLj-core2-2.0.0-SNAPSHOT-withDependencies.jar <in> -o <out> -p <prefix> -v <version>
    
    
