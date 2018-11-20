# xck
a simple jvm wrote by java just for realized how jvm works.

It has only one useful command.

U can input "classpath;className" to invoke this classFile

if the parameter "className" are some jdk's library's classFile.It can also be loaded and u can see some informations.

Note:

    1.This small toy only support jdk1.8 and u'd better already setting "JAVA_HOME" environment variable.
    Because the rootClassPath depends on jdk1.8's librarys.Jdk1.9+'s catalogs are changed so it might not
    be able to load jdk's library.
    
    2.This toy only supports a few features.U can use it do some simple computings.But don't use array.
    I haven't finished it.
    
    3.If u find some bugs about it.It's very appreciate if u can fix that.Also u can give an issue.But 
    maybe i won't go to fix it.
