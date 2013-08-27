# libSBOLj: SBOL Java library

[libSBOLj](https://github.com/SynBioDex/libSBOLj) provides the core Java interfaces and their implementation for 
the [Synthetic Biology Open Language (SBOL)](http://www.sbolstandard.org/specification). The library provides an API to 
work with SBOL objects, the functionality to read and write SBOL documents as XML files, and a validator to check the 
correctness of SBOL models. 

## Getting the libSBOLj source

The easiest way to get the libSBOLj source is to Clone the github repository:

    git clone git://github.com/SynBioDex/libSBOLj.git

## Building libSBOLj

The libSBOLj library is built using maven:

    maven install

This will recursively build the libSBOLj project and sub-projects.

## Using libSBOLj


### libSBOLj command line

libSBOLj comes with a command-line interface (CLI) that can be used to validate SBOL files. After you build the 
libSBOLj.jar as described above, you can use it to validate files as follows:

    java -jar libSBOLj.jar examples/data/BBa_I0462.xml
    
If validation is successful, the program will print the contents of the SBOL document. Use --quite option if you want to
suppress this output. 

If validation fails with an error, there will be a message printed about the validation error. To see an example, try
the following command: 
    
    java -jar libSBOLj.jar test/data/invalid01_missing_displayId.xml
    
### libSBOLj API    

The programs provided under [examples/src](https://github.com/SynBioDex/libSBOLj/tree/master/examples/src/org/sbolstandard/core/examples) 
directory show various examples of using libSBOLj library. You can compile these examples by running:

    ant examples

This will create libSBOLj-examples.jar in the same directory and an example program can be run as follows: 
    
    java -cp libSBOLj-examples.jar org.sbolstandard.core.examples.Example01_Basics

More detailed information about libSBOLj API can be found in the javadocs. To generate the libSBOLj javadocs run:

    ant javadoc
    
The javadoc output will be written to "javadocs" directory. 
    
## Serialization in libSBOLj

libSBOLj provides support for reading and writing libSBOLj documents in XML syntax. The structure of the documents is 
defined in an XML schema file that can be found in the [schema](https://github.com/SynBioDex/libSBOLj/tree/master/schema) directory. 
    
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
