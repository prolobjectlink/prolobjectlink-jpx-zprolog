@echo off

SET CURRENT_DIRECTORY=%~dp0
for %%B in (%CURRENT_DIRECTORY%.) do set PROLOBJECTLINK_HOME=%%~dpB
SET CLASSPATH=%PROLOBJECTLINK_HOME%lib\*

: default jdk
java -classpath %CLASSPATH% org.prolobjectlink.db.prolog.zprolog.ZPrologDatabaseConsole -m
java -classpath %CLASSPATH% org.prolobjectlink.db.prolog.zprolog.ZPrologDatabaseConsole -z 9110