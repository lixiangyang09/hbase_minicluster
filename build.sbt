
lazy val root = (project in file(".")).
  settings(
    name := "hbase-minicluster",
    version := "1.1-SNAPSHOT",
    scalaVersion := "2.12.5",
    fork := true,
  )


val hbaseVersion = "2.0.0-beta-2"
val hadoopVersion = "2.8.3"


val hbaseClient = "org.apache.hbase" % "hbase-client" % hbaseVersion
val hbaseServer = "org.apache.hbase" % "hbase-server" % hbaseVersion
val hbaseServerTest = "org.apache.hbase" % "hbase-server" % hbaseVersion % Test classifier "tests"
val hbaseUtil = "org.apache.hbase" % "hbase-testing-util" % hbaseVersion
val hbaseUtilTest = "org.apache.hbase" % "hbase-testing-util" % hbaseVersion % Test classifier "tests"
val hbaseZookeeper = "org.apache.hbase" % "hbase-zookeeper" % hbaseVersion
val hbaseZookeeperTest = "org.apache.hbase" % "hbase-zookeeper" % hbaseVersion % Test classifier "tests"
val hbaseCommon = "org.apache.hbase" % "hbase-common" % hbaseVersion
val hbaseCommonTest = "org.apache.hbase" % "hbase-common" % hbaseVersion % Test classifier "tests"

val hbaseMetricAPI = "org.apache.hbase" % "hbase-metrics-api" % hbaseVersion
val hbaseMetric = "org.apache.hbase" % "hbase-metrics" % hbaseVersion

val hbaseDeps = Seq(hbaseClient, hbaseServer, hbaseServerTest,
  hbaseUtil, hbaseUtilTest, hbaseZookeeper, hbaseZookeeperTest,
  hbaseCommon, hbaseCommonTest, hbaseMetric, hbaseMetricAPI)

val hadoopCommon = "org.apache.hadoop" % "hadoop-common" % hadoopVersion % "provided"
val hadoopCommonTest = "org.apache.hadoop" % "hadoop-common" % hadoopVersion  % Test classifier "tests"
val hadoopHdfs = "org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion % "provided"
val hadoopHdfsTest = "org.apache.hadoop" % "hadoop-hdfs" % hadoopVersion % Test classifier "tests"
val hadoopCompat = "org.apache.hbase" % "hbase-hadoop-compat" % hbaseVersion
val hadoopCompatTest = "org.apache.hbase" % "hbase-hadoop-compat" % hbaseVersion  % Test classifier "tests"
val hadoopCompat2 = "org.apache.hbase" % "hbase-hadoop2-compat" % hbaseVersion
val hadoopCompat2Test = "org.apache.hbase" % "hbase-hadoop2-compat" % hbaseVersion  % Test classifier "tests"


val hadoopDeps = Seq(hadoopCommon, hadoopCommonTest, hadoopHdfs, hadoopHdfsTest,
  hadoopCompat, hadoopCompatTest,
  hadoopCompat2, hadoopCompat2Test)


val scalaTest = "org.scalatest" %% "scalatest" % "3.2.0-SNAP10" % Test
val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.13.5" % Test

libraryDependencies ++= hbaseDeps
libraryDependencies ++= hadoopDeps
libraryDependencies ++= Seq(scalaTest, scalaCheck)

