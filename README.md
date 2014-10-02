GeoChat - Server Project
===

Server side implementation for the [geolocation chat](https://github.com/emeryduh/AndroidProject) application - a chat application that allows users to communicate in chat room based on their geolocation.
Project Management documentation is located on [Google Drive](https://drive.google.com/folderview?id=0B-zlvMnzIsA_c0JGc0w0UXFjVVk&usp=sharing_eil)

The server is deployed to the [OpenShift cloud](http://geochat-slovit.rhcloud.com).

For project implementation we used following frameworks, libraries and tools:
- [Jersey Framework](https://jersey.java.net)
- [Google Guava Libraries](https://code.google.com/p/guava-libraries/)
- [Lombok Project](http://projectlombok.org/index.html)
- [Apache Maven](http://maven.apache.org)

Development
---
To contribute to the project first create a feature branch either manually or utilize (GitHub Flow)[https://guides.github.com/introduction/flow/index.html] tools. 
To propagate your changes create a [pull request](https://help.github.com/articles/using-pull-requests). Developers who commit to `develop` or `master` branches directly will be severely punished.

Setup
---

Clone the repository
`git clone https://github.com/slovit/geochat-server.git`

Eclipse Setup
---

The projects depends on (Project Lombok)[http://projectlombok.org/index.html]. Please install it before importing the project.
Follow the [instructions](http://projectlombok.org/features/index.html) in the chapter `Running Lombok \ On eclipse`

Import the project as existing maven project. From Eclipse do:
`File -> Import -> Existing Maven Project -> (Navigate to geochat-server) -> Finish`

Maven Builds
---

Install Maven:

[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi)

Build
---

To build the server project

`mvn package`
