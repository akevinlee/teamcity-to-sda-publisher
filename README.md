TeamCity to Serena Deploy Plugin
================================

This plugin integrates TeamCity with Serena Deployment Automation. It allows you to publish the artifacts from a successful build into Deployment Automation and then
optionally deploy the uploaded version to an environment.

Build Instructions
------------------

You will need Apache Maven and a Java JDK 1.6 or later to build the plugin.

To successfully build the plugin using maven you will need to install the Serena VFS libraries into your local maven (.m2) cache.
To do this navigate to the WEB-INF\lib directory of a Serena Deployment Automation installation and execute the following commands:

>mvn install:install-file -Dfile=.\serenara-client-CURRENT.jar -DgroupId=com.urbancode.vfs -DartifactId=serenara-client -Dversion=5.1.3 -Dpackaging=jar
>mvn install:install-file -Dfile=.\vfs-CURRENT.jar -DgroupId=com.urbancode.vfs -DartifactId=serenara-vfs -Dversion=5.1.3 -Dpackaging=jar
>mvn install:install-file -Dfile=.\commons-fileutils-CURRENT.jar  -DgroupId=com.urbancode.vfs  -DartifactId=commons-fileutils -Dversion=5.1.3 -Dpackaging=jar
>mvn install:install-file -Dfile=.\commons-util-CURRENT.jar -DgroupId=com.urbancode.vfs -DartifactId=commons-util -Dversion=5.1.3 -Dpackaging=jar

Then edit the pom.xml file in the root directory of the plugin and change the line:

    <sda-version>5.1.3</sda-version>

to reflect the version of SDA you are using.

See README.docx for instructions on how to use the plugin.

Thank you!

klee@serena.com

