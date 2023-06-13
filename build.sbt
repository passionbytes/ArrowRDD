name := "spark-arrow"

version := "1.0"

organization := "com.passion"

scalaVersion := "2.12.4"
javacOptions ++= Seq("-source", "11")

compileOrder := CompileOrder.JavaThenScala
mainClass := Some("com.passion.Sample")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.2.1"  ,
  "org.apache.spark" %% "spark-sql" % "3.2.1" ,
  "org.apache.hadoop" % "hadoop-client" % "2.7.3",
  "org.apache.hadoop" % "hadoop-common" % "2.7.3",
  "org.apache.arrow" % "arrow-vector" % "12.0.0",
  "org.apache.arrow" % "arrow-memory" % "12.0.0",
  "org.apache.arrow" % "arrow-format" % "12.0.0",
"org.apache.arrow" % "flight-grpc" % "12.0.0",
 ("org.apache.arrow" % "flight-core" % "2.0.0").exclude("io.netty", "netty-transport-native-unix-common").exclude("io.netty", "netty-transport-native-kqueue")
)

scalacOptions in Test ++= Seq("-Yrangepos")
assemblyMergeStrategy in assembly := {
 case PathList("META-INF", xs @ _*) => MergeStrategy.discard
 case x => MergeStrategy.first
}
