#!/bin/bash
OOZIE_USERHOST="root@127.0.0.1"
OOZIE_PORT=2222
PASSWORD="krish" 

sshpass -p $PASSWORD ssh -p $OOZIE_PORT $OOZIE_USERHOST 'mkdir -p   /user/root/apps/spark-shell-job'

sshpass -p $PASSWORD scp -P 2222 target/scala-2.11/sparkpro-assembly-0.1.0-SNAPSHOT.jar  root@127.0.0.1:/root/apps/spark-shell-job

sshpass -p $PASSWORD scp -P 2222 spark-shell-job/*  root@127.0.0.1:/root/apps/spark-shell-job
#sshpass -p $PASSWORD ssh -p $OOZIE_PORT $OOZIE_USERHOST 'hdfs dfs -rm -R /tmp/shakespeareWordCount'

sshpass -p $PASSWORD ssh -p $OOZIE_PORT $OOZIE_USERHOST 'hdfs dfs -put -f /root/apps/spark-shell-job /user/root/apps'

sshpass -p $PASSWORD ssh -p $OOZIE_PORT $OOZIE_USERHOST 'oozie job -oozie http://localhost:11000/oozie  -config /root/apps/spark-shell-job/job.properties -run'
