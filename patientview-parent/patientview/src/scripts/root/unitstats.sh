#!/bin/sh

# Local variables
THISDIR="/root/scripts"
TMPDIR="/usr/local/etc/unitstatstemp"          # change this in stats.sql too!!!
LIVEDIR="/usr/local/etc/unitstats"
DBUSR=
DBPWD=

# Get last months date
MTH=`date --date="1 month ago" +%m`
YR=`date --date="1 month ago" +%Y`

# Loop round all units
while read UNITLOGON
do

  echo 'unituser [$UNITLOGON]'

  # use the part before the underscore
  UNITCODE="$(echo $UNITLOGON | cut -d_ -f1)"
  echo $UNITCODE

   # clear out files
   rm -f $THISDIR/run_stats.sql
   rm -f $TMPDIR/$UNITCODE-$YR-$MTH.csv
   rm -f $LIVEDIR/$UNITCODE-$YR-$MTH.csv

   # make temp sql query for unit
   sed "s/UNIT/$UNITCODE/g;s/YR/$YR/g;s/MTH/$MTH/g" $THISDIR/stats.sql > $THISDIR/run_stats.sql

   # run the sql query
   mysql -u$DBUSR -p$DBPWD < $THISDIR/run_stats.sql

   # concatenate the headers file with the newly minited log csv file
   cat $THISDIR/columntitlesunitstats.txt $TMPDIR/$UNITCODE-$YR-$MTH.csv > $LIVEDIR/$UNITCODE-$YR-$MTH.csv

   # tidy up unit temp file
   rm -f $TMPDIR/$UNITCODE-$YR-$MTH.csv
done < $THISDIR/allunits.txt

# tidy up temp sql query file
rm -f $THISDIR/run_stats.sql
