#!/bin/bash
OOZIE_USERHOST="root@127.0.0.1"
OOZIE_PORT=2222
PASSWORD="krish"
NAME="spark-starter"

echo $NAME
#ssh -p $OOZIE_PORT $OOZIE_USERHOST 'rm -rf ~/apps/$NAME; mkdir -p ~/apps/$NAME;'

sshpass -p $PASSWORD scp -P 2222 target/scala-2.11/sparkpro-assembly-0.1.0-SNAPSHOT.jar  root@127.0.0.1:/root/apps/sparkpro
#sshpass -p $PASSWORD ssh -p $OOZIE_PORT $OOZIE_USERHOST 'hdfs dfs -rm -R /tmp/shakespeareWordCount'

#sshpass -p $PASSWORD ssh -p $OOZIE_PORT $OOZIE_USERHOST 'hdfs dfs -put -f /root/apps/spark-starter /user/root/apps'
