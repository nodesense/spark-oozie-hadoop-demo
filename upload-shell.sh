#!/bin/bash
OOZIE_USERHOST="root@127.0.0.1"
OOZIE_PORT=2222
PASSWORD="krish" 

sshpass -p $PASSWORD scp -P 2222 shell/*  root@127.0.0.1:/root/apps/shell
#sshpass -p $PASSWORD ssh -p $OOZIE_PORT $OOZIE_USERHOST 'hdfs dfs -rm -R /tmp/shakespeareWordCount'

sshpass -p $PASSWORD ssh -p $OOZIE_PORT $OOZIE_USERHOST 'hdfs dfs -put -f /root/apps/shell /user/root/apps'

sshpass -p $PASSWORD ssh -p $OOZIE_PORT $OOZIE_USERHOST 'oozie job -oozie http://localhost:11000/oozie  -config /root/apps/shell/job.properties -run'
