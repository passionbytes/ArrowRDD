name := "spark-arrow"

version := "1.0"

organization := "com.passion"

scalaVersion := "2.12.6"
javacOptions ++= Seq("-source", "1.8")

compileOrder := CompileOrder.JavaThenScala
mainClass := Some("com.passion.Sample")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.0.0"  ,
  "org.apache.spark" %% "spark-sql" % "3.0.0" ,
  "org.apache.hadoop" % "hadoop-client" % "2.7.3",
  "org.apache.hadoop" % "hadoop-common" % "2.7.3",
  "org.apache.arrow" % "arrow-vector" % "0.17.1",
  "org.apache.arrow" % "arrow-memory" % "0.17.1"

)

scalacOptions in Test ++= Seq("-Yrangepos")
assemblyMergeStrategy in assembly := {
 case PathList("META-INF", xs @ _*) => MergeStrategy.discard
 case x => MergeStrategy.first
}
