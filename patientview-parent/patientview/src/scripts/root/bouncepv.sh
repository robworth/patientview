#!/bin/sh

EXPECTED_ARGS=1

if [ $# -ne $EXPECTED_ARGS ]
then
  echo "Usage: bouncepv.sh <java process number>"
  exit 
fi

/opt/tomcat_4/bin/shutdown.sh

kill -9 $1

/opt/tomcat_4/bin/startup.sh
