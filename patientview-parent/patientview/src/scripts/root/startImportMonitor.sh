#!/bin/sh

DATE=`date`
OUTPUT=$(ps aux | grep -v grep | grep -v startImportMonitor.sh | grep importMonitor)
echo $OUTPUT
if [ "${#OUTPUT}" -gt 0 ] ;
then
	echo "Import monitor is already running."
else
	(java -jar /home/patientview/importMonitorProduction.jar)  &
fi
