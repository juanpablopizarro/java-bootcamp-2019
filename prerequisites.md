
## Pre Requisites

### JDK
Java as a platform has a development kit called JDK (Java Development Kid) and a Runtime Environment (JRE) the difference between both is, as its name says, that one of them is to write java programs and the other to run it. In order to develop in java you have to install JDK (it has embedded the JRE). Let's install it using the following guide:

- [Installation on Linux](https://docs.oracle.com/javase/10/install/installation-jdk-and-jre-linux-platforms.htm#JSJIG-GUID-737A84E4-2EFF-4D38-8E60-3E29D1B884B8)
- [Installation on Mac](https://docs.oracle.com/javase/10/install/installation-jdk-and-jre-macos.htm#JSJIG-GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE)
- [Installation on Windows](https://docs.oracle.com/javase/10/install/installation-jdk-and-jre-microsoft-windows-platforms.htm#JSJIG-GUID-A7E27B90-A28D-4237-9383-A58B416071CA)

Obviously, you can always google the installation process and find some youtube video :)

### IDE
The Integration Development Environment is up to you, you can use whatever you want. Most of Java developers prefers Eclipse or IntelliJ Idea, I prefer Microsoft Visual Code for most of the languages I use. One thing to have in mind is that all the Java IDE creates it owns configuration files and folders and these has the configuration for YOUR environment, **please** do not push that to the shared repository. For git repos you can mention that stuff into the .gitignore file for untrack them all.

As we mention you can user [Eclispe](https://www.eclipse.org/downloads/packages/), [intelliJ IDEA](https://www.jetbrains.com/idea/download) or [Microsoft Visual Code](https://code.visualstudio.com/download). All of them are good Java IDE and all has a plugin for Maven or Spring. In case of Eclipse, use the JEE version. I prefer Microsoft Visual Code because is lightweight and modern, you can find a lot of useful plugins, but take in account that an IDE for me is just a code editor, I use a lot the console or terminal. Whatever you download and install, please find and see some videos in order to understand what plugin you will need or how to install them.

### Maven
According to [Apache Maven](https://maven.apache.org/) website:

>Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.

We will use it instead [gradle](https://gradle.org/) without any strong argument, just because we choose it. You can use gradle instead, again is up to you.

Using Maven we will construct and package the jar file in order to execute our server. Follow this guide to install and configure it.

- [Download](https://maven.apache.org/download.cgi)
- [Install](https://maven.apache.org/install.html)
- [Run](https://maven.apache.org/install.html)

### GIT
Git is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency.

- [Download for Mac](http://git-scm.com/download/mac)
- [Download for Windows](http://msysgit.github.io/)
- [Download for Windows](http://git-scm.com/book/en/Getting-Started-Installing-Git)

We will use git to upload our code to github repo. Here you will find a cheatsheet that might help:
[Git cheatsheet](http://rogerdudler.github.io/git-guide/index.html)
