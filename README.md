# libSBOLj: SBOL Java library

[libSBOLj](https://github.com/SynBioDex/libSBOLj) provides the core Java interfaces and their implementation for 
the [Synthetic Biology Open Language (SBOL)](http://www.sbolstandard.org/sbolstandard/specification). The library provides an API to 
work with SBOL objects, the functionality to read and write SBOL documents as XML files, and a validator to check the 
correctness of SBOL models. 

## Getting the libSBOLj source

1. Create a GitHub account. [link](https://github.com/)

2. Download and set up Git. [link](https://help.github.com/articles/set-up-git)

3. Fork the libSBOLj repository and clone it to your machine. [link](https://help.github.com/articles/fork-a-repo)

4. Download and set up Maven. [link](http://maven.apache.org/download.cgi)

5. Change to your libSBOLj directory via the command line and execute the following command:

    mvn package

This will create the libSBOLj JAR file (core-1.0.0-SNAPSHOT.jar) and place it into the core/target subdirectory. [link](http://maven.apache.org/guides/getting-started/index.html)

## Using libSBOLj


### libSBOLj command line

libSBOLj comes with a command-line interface (CLI) that can be used to validate SBOL files. After you build the 
libSBOLj-1.0.0-SNAPSHOT.jar as described above, you can use it to validate files as follows after changing to the core/target subdirectory:

    java -jar core-1.0.0-SNAPSHOT.jar test-classes/test/data/BBa_I0462.xml
    
If validation is successful, the program will print the contents of the SBOL document. Use --quit option if you want to
suppress this output. 

If validation fails with an error, there will be a message printed about the validation error. To see an example, try
the following command: 
    
    java -jar core-1.0.0-SNAPSHOT.jar test-classes/test/data/invalid01_missing_displayId.xml
    
## Serialization in libSBOLj

libSBOLj provides support for reading and writing libSBOLj documents in XML syntax. The structure of the documents is defined in two XML schema (XSD) files that can be found in the [resources](https://github.com/SynBioDex/libSBOLj/tree/master/src/main/resources) directory.

A very simple SBOL document looks like this in XML:
    
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <rdf:RDF xmlns="http://sbols.org/v1#" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
        <DnaComponent rdf:about="http://example.com/MyDnaComponent">
            <displayId>MyDnaComponent</displayId>
            <name>myDNA</name>
            <description>This is a very simple example</description>
        </DnaComponent>
    </rdf:RDF>
    
A more complete example can be found in [examples/data](https://github.com/SynBioDex/libSBOLj/tree/master/examples/data)
directory.     

Note that, the XML serialization of SBOL documents have been designed to be compatible with 
[Resource Description Format (RDF)](http://www.w3.org/RDF/). Any valid SBOL XML file can be parsed by a standard RDF
tool that supports [RDF/XML syntax](http://www.w3.org/TR/REC-rdf-syntax/). But different RDF serializations will not be
valid if they do not match the constraints defined in the SBOL XML schema and cannot be read by libSBOLj. 
        
libSBOLj provides also a more readable, human-friendly output format that aligns more closely with the SBOL object 
model defined in the specification. The above example would look as follows in this syntax:
     
    SBOLDocument [
       DnaComponent [
          uri: http://example.com/MyDnaComponent
          displayId: MyDnaComponent
          description: This is a very simple example
       ]
    ]  
    
This format is only intended for presentation purposes and not to exchange libSBOLj structures and the library does not 
provide any means to read this syntax.    

