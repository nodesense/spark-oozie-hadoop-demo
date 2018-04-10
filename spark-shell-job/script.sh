#echo "I ran spark shell ! `date`" > /tmp/output

# /usr/bin/spark-submit --class ai.nodesense.spark.WordCount /root/apps/sparkpro/sparkpro-assembly-0.1.0-SNAPSHOT.jar

/usr/bin/spark-submit  --class ai.nodesense.spark.WordCount --master yarn-client --driver-memory 500m --num-executors 1 --executor-memory 500m --executor-cores 1 sparkpro-assembly-0.1.0-SNAPSHOT.jar 3