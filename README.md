# libSBOL: SBOL Java library

[libSBOL](https://github.com/SynBioDex/libSBOLcore) provides the core Java interfaces and their implementation for 
the [Synthetic Biology Open Language (SBOL)](http://www.sbolstandard.org/specification). The library provides an API to 
work with SBOL objects, the functionality to read and write SBOL documents as XML files, and a validator to check the 
correctness of SBOL models. 

## Building libSBOL

First, clone the repository:

    git clone git://github.com/SynBioDex/libSBOL.git

To build libSBOL use the following command:

    ant clean jar teat

This command will clean any previous binaries, create a libSBOL.jar in the same directory and also run the unit tests 
to make sure that there are on errors in the library.    

## Using libSBOL


### libSBOL command line

libSBOL comes with a command-line interface (CLI) that can be used to validate SBOL files. After you build the 
libSBOL.jar as described above, you can use it to validate files as follows:

    java -jar libSBOL.jar examples/data/BBa_I0462.xml
    
If validation is successful, the program will print the contents of the SBOL document. Use --quite option if you want to
suppress this output. 

If validation fails with an error, there will be a message printed about the validation error. To see an example, try
the following command: 
    
    java -jar libSBOL.jar test/data/invalid01_missing_displayId.xml
    
### libSBOL API    

The programs provided under [examples/src](https://github.com/SynBioDex/libSBOLcore/tree/master/examples/src/org/sbolstandard/core/examples) 
directory show various examples of using libSBOL library. You can compile these examples by running:

    ant examples

This will create libSBOL-examples.jar in the same directory and an example program can be run as follows: 
    
    java -cp libSBOL-examples.jar org.sbolstandard.core.examples.Example01_Basics

More detailed information about libSBOL API can be found in the javadocs. To generate the libSBOL javadocs run:

    ant javadoc
    
The javadoc output will be written to "javadocs" directory. 
    
## Serialization in libSBOL

libSBOL provides support for reading and writing libSBOL documents in XML syntax. The structure of the documents is 
defined in an XML schema file that can be found in the [schema](https://github.com/SynBioDex/libSBOLcore/tree/master/schema) directory. 
    
A very simple SBOL document looks like this in XML:
    
    <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
    <rdf:RDF xmlns="http://sbols.org/v1#" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
        <DnaComponent rdf:about="http://example.com/MyDnaComponent">
            <displayId>MyDnaComponent</displayId>
            <name>myDNA</name>
            <description>This is a very simple example</description>
        </DnaComponent>
    </rdf:RDF>
    
A more complete example can be found in [examples/data](https://github.com/SynBioDex/libSBOLcore/tree/master/examples/data)
directory.     

Note that, the XML serialization of SBOL documents have been designed to be compatible with 
[Resource Description Format (RDF)](http://www.w3.org/RDF/). Any valid libSBOL XML file can be parsed by a standard RDF
tool that supports [RDF/XML syntax](http://www.w3.org/TR/REC-rdf-syntax/). But different RDF serializations will not be
valid if they do not match the constraints defined in the SBOL XML schema and cannot be read by libSBOL. 
        
libSBOL provides also a more readable, human-friendly output format that aligns more closely with the libSBOL object 
mode. The above example would look as follows in this syntax:
     
    SBOLDocument [
       DnaComponent [
          uri: http://example.com/MyDnaComponent
          displayId: MyDnaComponent
          description: This is a very simple example
       ]
    ]  
    
This format is only intended for presentation purposes and not to exchange libSBOL structures and the library does not 
provide any means to read this syntax.    