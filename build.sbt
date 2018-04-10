import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "ai.nodesense",
      scalaVersion := "2.11.12",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "sparkpro",
    libraryDependencies += scalaTest % Test,

    libraryDependencies ++= {
      val sparkVer = "2.2.0"
      Seq(
        "org.apache.spark" %% "spark-core" % sparkVer  % "provided" ,
        "org.apache.spark" %% "spark-sql" % sparkVer  % "provided"  ,
        "org.apache.spark" %% "spark-streaming" % sparkVer  % "provided" ,
        "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVer
      )
    },

    // https://mvnrepository.com/artifact/org.apache.spark/spark-streaming-kafka-0-10

    libraryDependencies += "org.apache.kafka" %% "kafka" % "1.0.0",
    libraryDependencies += "org.apache.kafka" % "kafka-clients" % "1.0.0",

    libraryDependencies += "org.apache.hadoop" % "hadoop-hdfs" % "2.7.3"  % "provided" ,



      libraryDependencies += "mysql" % "mysql-connector-java" % "6.0.6",
    // https://mvnrepository.com/artifact/log4j/log4j
    libraryDependencies += "log4j" % "log4j" % "1.2.17",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.7",
    // https://mvnrepository.com/artifact/com.typesafe.play/play-json-joda
    libraryDependencies += "com.typesafe.play" %% "play-json-joda" % "2.6.9"





  )
  
  //mainClass := Some("ai.nodesense.spark.SparkHDFS")


assemblyMergeStrategy in assembly := {
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
    case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
    case PathList("org", "apache", xs @ _*) => MergeStrategy.last
    case PathList("com", "google", xs @ _*) => MergeStrategy.last
    case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
    case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
    case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
    case "about.html" => MergeStrategy.rename
    case "META-INF/ECLIPSEF.RSA" => MergeStrategy.last
    case "META-INF/mailcap" => MergeStrategy.last
    case "META-INF/mimetypes.default" => MergeStrategy.last
    case "plugin.properties" => MergeStrategy.last
    case "log4j.properties" => MergeStrategy.last
    case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
}