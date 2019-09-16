#!/usr/bin/bash
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.zprolog.ZPrologDatabaseConsole -m
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.zprolog.ZPrologDatabaseConsole -z 9110