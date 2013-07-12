#!/bin/sh

# Local variables
THISDIR="/root/scripts"
DBUSR=
DBPWD=

# run the sql query
mysql -u$DBUSR -p$DBPWD < $THISDIR/userlog.sql
