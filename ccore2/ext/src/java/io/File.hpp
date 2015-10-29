// Generated from /Library/Java/JavaVirtualMachines/jdk1.7.0_60.jdk/Contents/Home/jre/lib/rt.jar

#pragma once

#include <atomic>
#include <fwd-${project.parent.artifactId}-core2.hpp>
#include <java/io/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/net/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/nio/file/fwd-${project.parent.artifactId}-core2.hpp>
#include <java/lang/Object.hpp>
#include <java/io/Serializable.hpp>
#include <java/lang/Comparable.hpp>

template<typename ComponentType, typename... Bases> struct SubArray;
namespace java
{
    namespace io
    {
typedef ::SubArray< ::java::io::Serializable, ::java::lang::ObjectArray > SerializableArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::Comparable, ObjectArray > ComparableArray;
    } // lang

    namespace io
    {
typedef ::SubArray< ::java::io::File, ::java::lang::ObjectArray, SerializableArray, ::java::lang::ComparableArray > FileArray;
    } // io

    namespace lang
    {
typedef ::SubArray< ::java::lang::CharSequence, ObjectArray > CharSequenceArray;
typedef ::SubArray< ::java::lang::String, ObjectArray, ::java::io::SerializableArray, ComparableArray, CharSequenceArray > StringArray;
    } // lang
} // java

struct default_init_tag;

class java::io::File
    : public virtual ::java::lang::Object
    , public virtual Serializable
    , public virtual ::java::lang::Comparable
{

public:
    typedef ::java::lang::Object super;

private:
    static bool $assertionsDisabled_;
    std::atomic< ::java::nio::file::Path* > filePath {  };
    static FileSystem* fs_;
    ::java::lang::String* path {  };
    static ::java::lang::String* pathSeparator_;
    static char16_t pathSeparatorChar_;
    int32_t prefixLength {  };
    static ::java::lang::String* separator_;
    static char16_t separatorChar_;
    static constexpr int64_t serialVersionUID { int64_t(301077366599181567LL) };
    File_PathStatus* status {  };

protected:
    void ctor(::java::lang::String* pathname);
    void ctor(::java::net::URI* uri);
    /*void ctor(::java::lang::String* pathname, int32_t prefixLength); (private) */
    /*void ctor(::java::lang::String* child, File* parent); (private) */
    void ctor(::java::lang::String* parent, ::java::lang::String* child);
    void ctor(File* parent, ::java::lang::String* child);

public:
    virtual bool canExecute();
    virtual bool canRead();
    virtual bool canWrite();
    virtual int32_t compareTo(File* pathname);
    virtual bool createNewFile();
    static File* createTempFile(::java::lang::String* prefix, ::java::lang::String* suffix);
    static File* createTempFile(::java::lang::String* prefix, ::java::lang::String* suffix, File* directory);
    virtual bool delete_();
    virtual void deleteOnExit();
    bool equals(::java::lang::Object* obj) override;
    virtual bool exists();
    virtual File* getAbsoluteFile();
    virtual ::java::lang::String* getAbsolutePath();
    virtual File* getCanonicalFile();
    virtual ::java::lang::String* getCanonicalPath();
    virtual int64_t getFreeSpace();
    virtual ::java::lang::String* getName();
    virtual ::java::lang::String* getParent();
    virtual File* getParentFile();
    virtual ::java::lang::String* getPath();

public: /* package */
    virtual int32_t getPrefixLength();

public:
    virtual int64_t getTotalSpace();
    virtual int64_t getUsableSpace();
    int32_t hashCode() override;
    virtual bool isAbsolute();
    virtual bool isDirectory();
    virtual bool isFile();
    virtual bool isHidden();

public: /* package */
    bool isInvalid();

public:
    virtual int64_t lastModified();
    virtual int64_t length();
    virtual ::java::lang::StringArray* list();
    virtual ::java::lang::StringArray* list(FilenameFilter* filter);
    virtual FileArray* listFiles();
    virtual FileArray* listFiles(FilenameFilter* filter);
    virtual FileArray* listFiles(FileFilter* filter);
    static FileArray* listRoots();
    virtual bool mkdir();
    virtual bool mkdirs();
    /*void readObject(ObjectInputStream* s); (private) */
    virtual bool renameTo(File* dest);
    virtual bool setExecutable(bool executable);
    virtual bool setExecutable(bool executable, bool ownerOnly);
    virtual bool setLastModified(int64_t time);
    virtual bool setReadOnly();
    virtual bool setReadable(bool readable);
    virtual bool setReadable(bool readable, bool ownerOnly);
    virtual bool setWritable(bool writable);
    virtual bool setWritable(bool writable, bool ownerOnly);
    /*static ::java::lang::String* slashify(::java::lang::String* path, bool isDirectory); (private) */
    virtual ::java::nio::file::Path* toPath();
    ::java::lang::String* toString() override;
    virtual ::java::net::URI* toURI();
    virtual ::java::net::URL* toURL();
    /*void writeObject(ObjectOutputStream* s); (private) */

    // Generated
    File(::java::lang::String* pathname);
    File(::java::net::URI* uri);
    File(::java::lang::String* parent, ::java::lang::String* child);
    File(File* parent, ::java::lang::String* child);
protected:
    File(const ::default_init_tag&);


public:
    static ::java::lang::Class *class_();
    virtual int32_t compareTo(::java::lang::Object* o) override;

public: /* package */
    static bool& $assertionsDisabled();

private:
    static FileSystem*& fs();

public:
    static ::java::lang::String*& pathSeparator();
    static char16_t& pathSeparatorChar();
    static ::java::lang::String*& separator();
    static char16_t& separatorChar();

private:
    virtual ::java::lang::Class* getClass0();
};
