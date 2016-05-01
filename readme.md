WildFly 10.x / Hibernate Spatial 5.x and PostGIS Demonstrator
=============================================================

If you tried to get a project up and running the usual way - installing a jdbc driver, creating a datasource, connecting to that datasource - you probably ran into trouble. The cause for your troubles are classpath problems. Hibernate is unable to cope with the jdbc driver residing somewhere else.
Currently, two working solutions are known to me:

0. Packing both Hibernate and the jdbc drivers into a module
0. Move all jdbc related stuff into the project

This project demonstrates the second approach.

Before running this project
---------------------------
WildFly comes with a datasource preconfigured: ExampleDS. The default binding points to this ds and this is causing all sorts of trouble. So, before you begin, open the standalone.xml / standalone-full.xml / standalone-ha.xml / standalone-full-ha.xml (whichever you're using) and look for the following fragment:
```
<default-bindings context-service="java:jboss/ee/concurrency/context/default" datasource="java:jboss/datasources/ExampleDS" jms-connection-factory="java:jboss/DefaultJMSConnectionFactory" managed-executor-service="java:jboss/ee/concurrency/executor/default" managed-scheduled-executor-service="java:jboss/ee/concurrency/scheduler/default" managed-thread-factory="java:jboss/ee/concurrency/factory/default"/>
```
If it is there, remove it. Remember to shut WildFly down BEFORE making edits to the config - otherwise WildFly will overwrite your edits the next time it shuts down.

What this project does
----------------------
Nothing special, really. There is one entity, "Location", with two attributes: location (point) and description (string). There is also some basic infrastructure and a minimalistic interface (using jsf/primefaces). If you run the project, try entering coordinates and a description into the textfields and hit "Save". If the values show up in the table above, everything is working fine. Look into the comments for some more information.