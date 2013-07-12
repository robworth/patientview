#!/bin/sh

# Local variables
THISDIR="/root/scripts"

# Get last months date

# Loop round all units

for UNITLOGON in `cat /root/scripts/twounits.txt`

do
 
  echo "unituser [$UNITLOGON]"
  mkdir /home/$UNITLOGON/outgoing-temp

  mkdir /home/$UNITLOGON/incoming/outgoing

  chown $UNITLOGON /home/$UNITLOGON/incoming/outgoing
  chgrp $UNITLOGON /home/$UNITLOGON/incoming/outgoing
  chmod 0555 /home/$UNITLOGON/incoming/outgoing

done

echo "done"
