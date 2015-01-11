# SqlGuiA
A lightweight graphical SQL interface to manage databases

Author: Alejandro Lorente
E-Mail: eidansoft at gmail dot com
Web:	http://www.eidansoft.com

My intention is to create a tool to connect and use a database as ORACLE or MySql but much more
lightweight than [SQLDeveloper], and with the caracteristics that I miss in [SQLPal].

You need [Java] installed into your computer and [Maven] to compile it. I can't offer here a compiled
version of this application because to connect to Oracle database it's necesary the [Oracle JDBC] driver.
You need to download [Oracle JDBC] driver by yourself and after that compile the aplication with it.

To compile you need [Maven] installed properly and the Oracle ojdbc7.jar dependency. This dependency
is not present at [Maven] repositories, so you need to add it to your local repository manually.
You can download this dependency from [Oracle JDBC] download page.
After download it you can add it to your local repository executing:

mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=7 -Dpackaging=jar -Dfile=ojdbc7.jar -DgeneratePom=true

After that you can compile SqlGuiA with:

mvn clean install

And that's all, the aplication will be created at your 'target' folder, and you can execute it with:

java -jar SqlGuiA-1.0-SNAPSHOT.jar

[SQLDeveloper]: http://www.oracle.com/technetwork/developer-tools/sql-developer/downloads/index.html
[SQLPal]: http://www.pebblereports.com/sqlpal/
[Maven]: http://maven.apache.org/
[Oracle JDBC]: http://www.oracle.com/technetwork/database/features/jdbc/index-091264.html
[Java]: https://www.java.com/es/download/