#!/bin/sh

EXPECTED_ARGS=1

if [ $# -ne $EXPECTED_ARGS ]
then
  echo "Usage: bounceradar.sh <java process number>"
  exit 
fi

/opt/tomcat_2/bin/shutdown.sh

kill -9 $1

/opt/tomcat_2/bin/startup.sh
