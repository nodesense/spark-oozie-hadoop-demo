#!/bin/bash
OOZIE_USERHOST="root@127.0.0.1"
OOZIE_PORT=2222
PASSWORD="krish"
NAME="spark-starter"

echo $NAME

sshpass -p $PASSWORD scp -P 2222 data/yoochoose-clicks.csv  data/yoochoose-stream.csv root@127.0.0.1:/root/apps/sparkpro/data

