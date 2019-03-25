#!/usr/bin/bash
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.db.prolog.zprolog.ZPrologDatabaseConsole ${1+"$@"}