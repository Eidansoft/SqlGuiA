# SqlGuiA
A lightweight graphical SQL interface to manage databases

Author: Alejandro Lorente
E-Mail: eidansoft at gmail dot com
Web:	http://www.eidansoft.com

My intention is to create a tool to connect and use a database as ORACLE or MySql but much more
lightweight than [SQLDeveloper], and with the caracteristics that I miss in [SQLPal].

To compile properly with maven you need the Oracle
ojdbc7.jar dependency. It's not present at Maven
repositories, so you need to add it to your local
repository.
Y put a copy of ojdbc.jar file at ojdbc folder.
You can add it to your local repository executing:

mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=7 -Dpackaging=jar -Dfile=ojdbc7.jar -DgeneratePom=true

[SQLDeveloper]: http://www.oracle.com/technetwork/developer-tools/sql-developer/downloads/index.html
[SQLPal]: http://www.pebblereports.com/sqlpal/
