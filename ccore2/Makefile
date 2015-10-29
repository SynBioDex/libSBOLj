INCLUDES := $(INCLUDES) -Isrc -Iext/src
EXTRA_LIBS ?= -l$(NAME)-ext

CPPFLAGS := $(CPPFLAGS) $(INCLUDES)
CFLAGS := $(CFLAGS) -g -pipe
CXXFLAGS := $(CFLAGS) -std=gnu++11

BIN := bin
OBJ := obj

NAME := ${project.parent.artifactId}-core2

LIB = lib$(NAME).a
EXT_LIB = lib$(NAME)-ext.a

LIBS = $(BIN)/$(LIB) $(BIN)/$(EXT_LIB)

SRC = src
STUB = stub
NATIVE = native

SRCS = \
	src/org/sbolstandard/core2/AccessType.cpp \
	src/org/sbolstandard/core2/Annotation.cpp \
	src/org/sbolstandard/core2/Collection.cpp \
	src/org/sbolstandard/core2/ComparableVersion.cpp \
	src/org/sbolstandard/core2/ComparableVersion_IntegerItem.cpp \
	src/org/sbolstandard/core2/ComparableVersion_Item.cpp \
	src/org/sbolstandard/core2/ComparableVersion_ListItem.cpp \
	src/org/sbolstandard/core2/ComparableVersion_StringItem.cpp \
	src/org/sbolstandard/core2/Component.cpp \
	src/org/sbolstandard/core2/ComponentDefinition.cpp \
	src/org/sbolstandard/core2/ComponentInstance.cpp \
	src/org/sbolstandard/core2/Cut.cpp \
	src/org/sbolstandard/core2/DirectionType.cpp \
	src/org/sbolstandard/core2/FunctionalComponent.cpp \
	src/org/sbolstandard/core2/GenericLocation.cpp \
	src/org/sbolstandard/core2/GenericTopLevel.cpp \
	src/org/sbolstandard/core2/Identified.cpp \
	src/org/sbolstandard/core2/Interaction.cpp \
	src/org/sbolstandard/core2/Location.cpp \
	src/org/sbolstandard/core2/MapsTo.cpp \
	src/org/sbolstandard/core2/Model.cpp \
	src/org/sbolstandard/core2/Module.cpp \
	src/org/sbolstandard/core2/ModuleDefinition.cpp \
	src/org/sbolstandard/core2/OrientationType.cpp \
	src/org/sbolstandard/core2/Participation.cpp \
	src/org/sbolstandard/core2/Range.cpp \
	src/org/sbolstandard/core2/RefinementType.cpp \
	src/org/sbolstandard/core2/RestrictionType.cpp \
	src/org/sbolstandard/core2/SBOLDocument.cpp \
	src/org/sbolstandard/core2/SBOLFactory.cpp \
	src/org/sbolstandard/core2/SBOLReader.cpp \
	src/org/sbolstandard/core2/SBOLReader_SBOLPair.cpp \
	src/org/sbolstandard/core2/SBOLValidate.cpp \
	src/org/sbolstandard/core2/SBOLValidationException.cpp \
	src/org/sbolstandard/core2/SBOLWriter.cpp \
	src/org/sbolstandard/core2/Sbol1Terms.cpp \
	src/org/sbolstandard/core2/Sbol1Terms_Collection.cpp \
	src/org/sbolstandard/core2/Sbol1Terms_DNAComponent.cpp \
	src/org/sbolstandard/core2/Sbol1Terms_DNASequence.cpp \
	src/org/sbolstandard/core2/Sbol1Terms_SequenceAnnotations.cpp \
	src/org/sbolstandard/core2/Sbol2Terms.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Annotation.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Collection.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Component.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_ComponentDefinition.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_ComponentInstance.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Cut.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_FunctionalComponent.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_GenericLocation.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_GenericTopLevel.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Identified.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Interaction.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Location.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_MapsTo.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Model.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Module.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_ModuleDefinition.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_MultiRange.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Participation.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Range.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_Sequence.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_SequenceAnnotation.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_SequenceConstraint.cpp \
	src/org/sbolstandard/core2/Sbol2Terms_SequenceURI.cpp \
	src/org/sbolstandard/core2/Sequence.cpp \
	src/org/sbolstandard/core2/SequenceAnnotation.cpp \
	src/org/sbolstandard/core2/SequenceConstraint.cpp \
	src/org/sbolstandard/core2/SequenceOntology.cpp \
	src/org/sbolstandard/core2/SystemsBiologyOntology.cpp \
	src/org/sbolstandard/core2/TopLevel.cpp \
	src/org/sbolstandard/core2/URIcompliance.cpp \
	src/org/sbolstandard/core2/Version.cpp \


STUB_SRCS = \


NATIVE_SRCS = \


EXT_SRCS = \


EXT_STUB_SRCS = \
	ext/stub/java/io/BufferedInputStream-stub.cpp \
	ext/stub/java/io/BufferedOutputStream-stub.cpp \
	ext/stub/java/io/File-stub.cpp \
	ext/stub/java/io/FileInputStream-stub.cpp \
	ext/stub/java/io/FileOutputStream-stub.cpp \
	ext/stub/java/io/File_PathStatus-stub.cpp \
	ext/stub/java/io/File_TempDirectory-stub.cpp \
	ext/stub/java/io/FilterInputStream-stub.cpp \
	ext/stub/java/io/FilterOutputStream-stub.cpp \
	ext/stub/java/io/IOException-stub.cpp \
	ext/stub/java/io/InputStream-stub.cpp \
	ext/stub/java/io/OutputStream-stub.cpp \
	ext/stub/java/io/OutputStreamWriter-stub.cpp \
	ext/stub/java/io/PrintStream-stub.cpp \
	ext/stub/java/io/PrintWriter-stub.cpp \
	ext/stub/java/io/Reader-stub.cpp \
	ext/stub/java/io/StringReader-stub.cpp \
	ext/stub/java/io/Writer-stub.cpp \
	ext/stub/java/lang/AbstractStringBuilder-stub.cpp \
	ext/stub/java/lang/ArrayStoreException-stub.cpp \
	ext/stub/java/lang/Boolean-stub.cpp \
	ext/stub/java/lang/Character-stub.cpp \
	ext/stub/java/lang/Character_CharacterCache-stub.cpp \
	ext/stub/java/lang/Character_Subset-stub.cpp \
	ext/stub/java/lang/Character_UnicodeBlock-stub.cpp \
	ext/stub/java/lang/Character_UnicodeScript-stub.cpp \
	ext/stub/java/lang/Class-stub.cpp \
	ext/stub/java/lang/ClassCastException-stub.cpp \
	ext/stub/java/lang/ClassLoader-stub.cpp \
	ext/stub/java/lang/ClassLoader_NativeLibrary-stub.cpp \
	ext/stub/java/lang/ClassLoader_ParallelLoaders-stub.cpp \
	ext/stub/java/lang/Class_EnclosingMethodInfo-stub.cpp \
	ext/stub/java/lang/Class_MethodArray-stub.cpp \
	ext/stub/java/lang/Class_SecurityManagerHelper-stub.cpp \
	ext/stub/java/lang/Double-stub.cpp \
	ext/stub/java/lang/Enum-stub.cpp \
	ext/stub/java/lang/Error-stub.cpp \
	ext/stub/java/lang/Exception-stub.cpp \
	ext/stub/java/lang/IllegalArgumentException-stub.cpp \
	ext/stub/java/lang/IllegalStateException-stub.cpp \
	ext/stub/java/lang/Integer-stub.cpp \
	ext/stub/java/lang/Integer_IntegerCache-stub.cpp \
	ext/stub/java/lang/NullPointerException-stub.cpp \
	ext/stub/java/lang/Number-stub.cpp \
	ext/stub/java/lang/Object-stub.cpp \
	ext/stub/java/lang/RuntimeException-stub.cpp \
	ext/stub/java/lang/String-stub.cpp \
	ext/stub/java/lang/StringBuilder-stub.cpp \
	ext/stub/java/lang/String_CaseInsensitiveComparator-stub.cpp \
	ext/stub/java/lang/System-stub.cpp \
	ext/stub/java/lang/Thread-stub.cpp \
	ext/stub/java/lang/Thread_Caches-stub.cpp \
	ext/stub/java/lang/Thread_State-stub.cpp \
	ext/stub/java/lang/Thread_WeakClassKey-stub.cpp \
	ext/stub/java/lang/Throwable-stub.cpp \
	ext/stub/java/lang/Throwable_PrintStreamOrWriter-stub.cpp \
	ext/stub/java/lang/Throwable_SentinelHolder-stub.cpp \
	ext/stub/java/lang/Throwable_WrappedPrintStream-stub.cpp \
	ext/stub/java/lang/Throwable_WrappedPrintWriter-stub.cpp \
	ext/stub/java/lang/ref/Reference-stub.cpp \
	ext/stub/java/lang/ref/Reference_Lock-stub.cpp \
	ext/stub/java/lang/ref/Reference_ReferenceHandler-stub.cpp \
	ext/stub/java/lang/ref/SoftReference-stub.cpp \
	ext/stub/java/lang/ref/WeakReference-stub.cpp \
	ext/stub/java/math/BigInteger-stub.cpp \
	ext/stub/java/net/URI-stub.cpp \
	ext/stub/java/net/URI_Parser-stub.cpp \
	ext/stub/java/util/AbstractCollection-stub.cpp \
	ext/stub/java/util/AbstractList-stub.cpp \
	ext/stub/java/util/AbstractList_Itr-stub.cpp \
	ext/stub/java/util/AbstractList_ListItr-stub.cpp \
	ext/stub/java/util/AbstractMap-stub.cpp \
	ext/stub/java/util/AbstractMap_SimpleEntry-stub.cpp \
	ext/stub/java/util/AbstractMap_SimpleImmutableEntry-stub.cpp \
	ext/stub/java/util/AbstractQueue-stub.cpp \
	ext/stub/java/util/AbstractSet-stub.cpp \
	ext/stub/java/util/ArrayList-stub.cpp \
	ext/stub/java/util/ArrayList_Itr-stub.cpp \
	ext/stub/java/util/ArrayList_ListItr-stub.cpp \
	ext/stub/java/util/ArrayList_SubList-stub.cpp \
	ext/stub/java/util/Arrays-stub.cpp \
	ext/stub/java/util/Arrays_ArrayList-stub.cpp \
	ext/stub/java/util/Arrays_LegacyMergeSort-stub.cpp \
	ext/stub/java/util/Collections-stub.cpp \
	ext/stub/java/util/Collections_AsLIFOQueue-stub.cpp \
	ext/stub/java/util/Collections_CheckedCollection-stub.cpp \
	ext/stub/java/util/Collections_CheckedList-stub.cpp \
	ext/stub/java/util/Collections_CheckedMap-stub.cpp \
	ext/stub/java/util/Collections_CheckedMap_CheckedEntrySet-stub.cpp \
	ext/stub/java/util/Collections_CheckedMap_CheckedEntrySet_CheckedEntry-stub.cpp \
	ext/stub/java/util/Collections_CheckedRandomAccessList-stub.cpp \
	ext/stub/java/util/Collections_CheckedSet-stub.cpp \
	ext/stub/java/util/Collections_CheckedSortedMap-stub.cpp \
	ext/stub/java/util/Collections_CheckedSortedSet-stub.cpp \
	ext/stub/java/util/Collections_CopiesList-stub.cpp \
	ext/stub/java/util/Collections_EmptyEnumeration-stub.cpp \
	ext/stub/java/util/Collections_EmptyIterator-stub.cpp \
	ext/stub/java/util/Collections_EmptyList-stub.cpp \
	ext/stub/java/util/Collections_EmptyListIterator-stub.cpp \
	ext/stub/java/util/Collections_EmptyMap-stub.cpp \
	ext/stub/java/util/Collections_EmptySet-stub.cpp \
	ext/stub/java/util/Collections_ReverseComparator-stub.cpp \
	ext/stub/java/util/Collections_ReverseComparator2-stub.cpp \
	ext/stub/java/util/Collections_SetFromMap-stub.cpp \
	ext/stub/java/util/Collections_SingletonList-stub.cpp \
	ext/stub/java/util/Collections_SingletonMap-stub.cpp \
	ext/stub/java/util/Collections_SingletonSet-stub.cpp \
	ext/stub/java/util/Collections_SynchronizedCollection-stub.cpp \
	ext/stub/java/util/Collections_SynchronizedList-stub.cpp \
	ext/stub/java/util/Collections_SynchronizedMap-stub.cpp \
	ext/stub/java/util/Collections_SynchronizedRandomAccessList-stub.cpp \
	ext/stub/java/util/Collections_SynchronizedSet-stub.cpp \
	ext/stub/java/util/Collections_SynchronizedSortedMap-stub.cpp \
	ext/stub/java/util/Collections_SynchronizedSortedSet-stub.cpp \
	ext/stub/java/util/Collections_UnmodifiableCollection-stub.cpp \
	ext/stub/java/util/Collections_UnmodifiableList-stub.cpp \
	ext/stub/java/util/Collections_UnmodifiableMap-stub.cpp \
	ext/stub/java/util/Collections_UnmodifiableMap_UnmodifiableEntrySet-stub.cpp \
	ext/stub/java/util/Collections_UnmodifiableMap_UnmodifiableEntrySet_UnmodifiableEntry-stub.cpp \
	ext/stub/java/util/Collections_UnmodifiableRandomAccessList-stub.cpp \
	ext/stub/java/util/Collections_UnmodifiableSet-stub.cpp \
	ext/stub/java/util/Collections_UnmodifiableSortedMap-stub.cpp \
	ext/stub/java/util/Collections_UnmodifiableSortedSet-stub.cpp \
	ext/stub/java/util/Dictionary-stub.cpp \
	ext/stub/java/util/HashMap-stub.cpp \
	ext/stub/java/util/HashMap_Entry-stub.cpp \
	ext/stub/java/util/HashMap_EntryIterator-stub.cpp \
	ext/stub/java/util/HashMap_EntrySet-stub.cpp \
	ext/stub/java/util/HashMap_HashIterator-stub.cpp \
	ext/stub/java/util/HashMap_Holder-stub.cpp \
	ext/stub/java/util/HashMap_KeyIterator-stub.cpp \
	ext/stub/java/util/HashMap_KeySet-stub.cpp \
	ext/stub/java/util/HashMap_ValueIterator-stub.cpp \
	ext/stub/java/util/HashMap_Values-stub.cpp \
	ext/stub/java/util/HashSet-stub.cpp \
	ext/stub/java/util/Hashtable-stub.cpp \
	ext/stub/java/util/Hashtable_Entry-stub.cpp \
	ext/stub/java/util/Hashtable_EntrySet-stub.cpp \
	ext/stub/java/util/Hashtable_Enumerator-stub.cpp \
	ext/stub/java/util/Hashtable_Holder-stub.cpp \
	ext/stub/java/util/Hashtable_KeySet-stub.cpp \
	ext/stub/java/util/Hashtable_ValueCollection-stub.cpp \
	ext/stub/java/util/Locale-stub.cpp \
	ext/stub/java/util/Locale_Builder-stub.cpp \
	ext/stub/java/util/Locale_Cache-stub.cpp \
	ext/stub/java/util/Locale_Category-stub.cpp \
	ext/stub/java/util/Locale_LocaleKey-stub.cpp \
	ext/stub/java/util/Locale_LocaleNameGetter-stub.cpp \
	ext/stub/java/util/Properties-stub.cpp \
	ext/stub/java/util/Properties_LineReader-stub.cpp \
	ext/stub/java/util/Scanner-stub.cpp \
	ext/stub/java/util/Stack-stub.cpp \
	ext/stub/java/util/Vector-stub.cpp \
	ext/stub/java/util/Vector_Itr-stub.cpp \
	ext/stub/java/util/Vector_ListItr-stub.cpp \
	ext/stub/java/util/regex/Matcher-stub.cpp \
	ext/stub/java/util/regex/Pattern-stub.cpp \
	ext/stub/java/util/regex/Pattern_All-stub.cpp \
	ext/stub/java/util/regex/Pattern_BackRef-stub.cpp \
	ext/stub/java/util/regex/Pattern_Begin-stub.cpp \
	ext/stub/java/util/regex/Pattern_Behind-stub.cpp \
	ext/stub/java/util/regex/Pattern_BehindS-stub.cpp \
	ext/stub/java/util/regex/Pattern_BitClass-stub.cpp \
	ext/stub/java/util/regex/Pattern_Block-stub.cpp \
	ext/stub/java/util/regex/Pattern_BmpCharProperty-stub.cpp \
	ext/stub/java/util/regex/Pattern_BnM-stub.cpp \
	ext/stub/java/util/regex/Pattern_BnMS-stub.cpp \
	ext/stub/java/util/regex/Pattern_Bound-stub.cpp \
	ext/stub/java/util/regex/Pattern_Branch-stub.cpp \
	ext/stub/java/util/regex/Pattern_BranchConn-stub.cpp \
	ext/stub/java/util/regex/Pattern_CIBackRef-stub.cpp \
	ext/stub/java/util/regex/Pattern_Caret-stub.cpp \
	ext/stub/java/util/regex/Pattern_Category-stub.cpp \
	ext/stub/java/util/regex/Pattern_CharProperty-stub.cpp \
	ext/stub/java/util/regex/Pattern_CharPropertyNames-stub.cpp \
	ext/stub/java/util/regex/Pattern_CharPropertyNames_CharPropertyFactory-stub.cpp \
	ext/stub/java/util/regex/Pattern_CharPropertyNames_CloneableProperty-stub.cpp \
	ext/stub/java/util/regex/Pattern_Conditional-stub.cpp \
	ext/stub/java/util/regex/Pattern_Ctype-stub.cpp \
	ext/stub/java/util/regex/Pattern_Curly-stub.cpp \
	ext/stub/java/util/regex/Pattern_Dollar-stub.cpp \
	ext/stub/java/util/regex/Pattern_Dot-stub.cpp \
	ext/stub/java/util/regex/Pattern_End-stub.cpp \
	ext/stub/java/util/regex/Pattern_First-stub.cpp \
	ext/stub/java/util/regex/Pattern_GroupCurly-stub.cpp \
	ext/stub/java/util/regex/Pattern_GroupHead-stub.cpp \
	ext/stub/java/util/regex/Pattern_GroupRef-stub.cpp \
	ext/stub/java/util/regex/Pattern_GroupTail-stub.cpp \
	ext/stub/java/util/regex/Pattern_LastMatch-stub.cpp \
	ext/stub/java/util/regex/Pattern_LastNode-stub.cpp \
	ext/stub/java/util/regex/Pattern_LazyLoop-stub.cpp \
	ext/stub/java/util/regex/Pattern_Loop-stub.cpp \
	ext/stub/java/util/regex/Pattern_Neg-stub.cpp \
	ext/stub/java/util/regex/Pattern_Node-stub.cpp \
	ext/stub/java/util/regex/Pattern_NotBehind-stub.cpp \
	ext/stub/java/util/regex/Pattern_NotBehindS-stub.cpp \
	ext/stub/java/util/regex/Pattern_Pos-stub.cpp \
	ext/stub/java/util/regex/Pattern_Prolog-stub.cpp \
	ext/stub/java/util/regex/Pattern_Ques-stub.cpp \
	ext/stub/java/util/regex/Pattern_Script-stub.cpp \
	ext/stub/java/util/regex/Pattern_Single-stub.cpp \
	ext/stub/java/util/regex/Pattern_SingleI-stub.cpp \
	ext/stub/java/util/regex/Pattern_SingleS-stub.cpp \
	ext/stub/java/util/regex/Pattern_SingleU-stub.cpp \
	ext/stub/java/util/regex/Pattern_Slice-stub.cpp \
	ext/stub/java/util/regex/Pattern_SliceI-stub.cpp \
	ext/stub/java/util/regex/Pattern_SliceIS-stub.cpp \
	ext/stub/java/util/regex/Pattern_SliceNode-stub.cpp \
	ext/stub/java/util/regex/Pattern_SliceS-stub.cpp \
	ext/stub/java/util/regex/Pattern_SliceU-stub.cpp \
	ext/stub/java/util/regex/Pattern_SliceUS-stub.cpp \
	ext/stub/java/util/regex/Pattern_Start-stub.cpp \
	ext/stub/java/util/regex/Pattern_StartS-stub.cpp \
	ext/stub/java/util/regex/Pattern_TreeInfo-stub.cpp \
	ext/stub/java/util/regex/Pattern_UnixCaret-stub.cpp \
	ext/stub/java/util/regex/Pattern_UnixDollar-stub.cpp \
	ext/stub/java/util/regex/Pattern_UnixDot-stub.cpp \
	ext/stub/java/util/regex/Pattern_Utype-stub.cpp \
	ext/stub/java/util/spi/LocaleNameProvider-stub.cpp \
	ext/stub/java/util/spi/LocaleServiceProvider-stub.cpp \
	ext/stub/javanet/staxutils/IndentingXMLStreamWriter-stub.cpp \
	ext/stub/javanet/staxutils/helpers/StreamWriterDelegate-stub.cpp \
	ext/stub/javax/json/Json-stub.cpp \
	ext/stub/javax/xml/namespace_/QName-stub.cpp \
	ext/stub/javax/xml/stream/FactoryConfigurationError-stub.cpp \
	ext/stub/javax/xml/stream/XMLInputFactory-stub.cpp \
	ext/stub/javax/xml/stream/XMLOutputFactory-stub.cpp \
	ext/stub/javax/xml/stream/XMLStreamException-stub.cpp \
	ext/stub/sun/util/locale/LocaleObjectCache-stub.cpp \
	ext/stub/sun/util/locale/LocaleObjectCache_CacheEntry-stub.cpp \
	ext/stub/uk/ac/intbio/core/io/turtle/TurtleIo-stub.cpp \
	ext/stub/uk/ac/intbio/core/io/turtle/TurtleIo_WSScanner-stub.cpp \
	ext/stub/uk/ac/ncl/intbio/core/datatree/Datatree-stub.cpp \
	ext/stub/uk/ac/ncl/intbio/core/datatree/IdentifiableDocument_Abstract-stub.cpp \
	ext/stub/uk/ac/ncl/intbio/core/datatree/Literal_Visitor-stub.cpp \
	ext/stub/uk/ac/ncl/intbio/core/datatree/NameTransformer-stub.cpp \
	ext/stub/uk/ac/ncl/intbio/core/datatree/NamespaceBinding-stub.cpp \
	ext/stub/uk/ac/ncl/intbio/core/datatree/PropertyValue_Visitor-stub.cpp \
	ext/stub/uk/ac/ncl/intbio/core/io/CoreIoException-stub.cpp \
	ext/stub/uk/ac/ncl/intbio/core/io/json/JsonIo-stub.cpp \
	ext/stub/uk/ac/ncl/intbio/core/io/json/StringifyQName-stub.cpp \
	ext/stub/uk/ac/ncl/intbio/core/io/rdf/RdfIo-stub.cpp \


EXT_NATIVE_SRCS = \
	ext/native/java/io/FileInputStream-native.cpp \
	ext/native/java/io/FileOutputStream-native.cpp \
	ext/native/java/lang/Class-native.cpp \
	ext/native/java/lang/ClassLoader-native.cpp \
	ext/native/java/lang/ClassLoader_NativeLibrary-native.cpp \
	ext/native/java/lang/Double-native.cpp \
	ext/native/java/lang/Object-native.cpp \
	ext/native/java/lang/String-native.cpp \
	ext/native/java/lang/System-native.cpp \
	ext/native/java/lang/Thread-native.cpp \
	ext/native/java/lang/Throwable-native.cpp \


MAIN_SRCS = \
	src/org/sbolstandard/core2/SBOLValidate-main.cpp \


MAINS = $(MAIN_SRCS:$(SRC)/%-main.cpp=$(BIN)/%)
OBJS = $(SRCS:$(SRC)/%.cpp=$(OBJ)/%.o)
STUB_OBJS = $(STUB_SRCS:$(STUB)/%.cpp=$(OBJ)/%.o)
NATIVE_OBJS = $(NATIVE_SRCS:$(NATIVE)/%.cpp=$(OBJ)/%.o)

EXT_OBJS = $(EXT_SRCS:ext/$(SRC)/%.cpp=$(OBJ)/%.o)
EXT_STUB_OBJS = $(EXT_STUB_SRCS:ext/$(STUB)/%.cpp=$(OBJ)/%.o)
EXT_NATIVE_OBJS = $(EXT_NATIVE_SRCS:ext/$(NATIVE)/%.cpp=$(OBJ)/%.o)

all: $(LIBS)

define cc-command
@mkdir -p $(dir $@); 
@echo Compile $<; $(CXX) $(CPPFLAGS) $(CXXFLAGS) -c -o $@ $<
endef

$(OBJS):$(OBJ)/%.o: $(SRC)/%.cpp | print-opts
	$(cc-command)
	
$(STUB_OBJS):$(OBJ)/%.o: $(STUB)/%.cpp | print-opts
	$(cc-command)

$(NATIVE_OBJS):$(OBJ)/%.o: $(NATIVE)/%.cpp | print-opts
	$(cc-command)

$(EXT_OBJS):$(OBJ)/%.o: ext/$(SRC)/%.cpp | print-opts
	$(cc-command)

$(EXT_STUB_OBJS):$(OBJ)/%.o: ext/$(STUB)/%.cpp | print-opts
	$(cc-command)

$(EXT_NATIVE_OBJS):$(OBJ)/%.o: ext/$(NATIVE)/%.cpp | print-opts
	$(cc-command)

%.a:
	@echo Archive $@
	@mkdir -p $(dir $@)
	@rm -f $@
	@ar rcs $@ $^

$(BIN)/$(LIB): $(OBJS) $(STUB_OBJS) $(NATIVE_OBJS)

$(BIN)/$(EXT_LIB): $(EXT_OBJS) $(EXT_STUB_OBJS) $(EXT_NATIVE_OBJS)

$(MAINS):$(BIN)/%:$(SRC)/%-main.cpp $(LIBS)
	@mkdir -p $(dir $@); 
	$(CXX) $(CPPFLAGS) $(CXXFLAGS) -L$(BIN) -o $@ $< -l$(NAME) $(EXTRA_LIBS)

mains: $(MAINS)

clean:
	rm -rf $(OBJ) $(BIN)

print-opts:
	@echo Building with \"$(CXX) $(CPPFLAGS) $(CXXFLAGS)\"
	
.PHONY: all mains clean print-opts
