#!/usr/bin/bash
kill $(jps -l | grep org.prolobjectlink.db.prolog.zprolog.ZPrologDatabaseConsole | awk '{print $1}')