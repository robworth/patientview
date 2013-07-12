#!/bin/sh

# Local variables
THISDIR="/root/scripts"
DBUSR=
DBPWD=

# Get last months date
MTH=`date --date="1 month ago" +%m`
YR=`date --date="1 month ago" +%Y`

# clear out files
rm -f $THISDIR/run_logunitstats.sql

# make temp sql query for unit
sed "s/YR/$YR/g;s/MTH/$MTH/g" $THISDIR/logunitstats.sql > $THISDIR/run_logunitstats.sql

# run the sql query
mysql -u$DBUSR -p$DBPWD < $THISDIR/run_logunitstats.sql

# tidy up temp sql query file
rm -f $THISDIR/run_logunitstats.sql
