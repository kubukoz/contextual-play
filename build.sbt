name := "contextual-play"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.6.0",
  "com.propensive" %% "contextual" % "1.0.1",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.chuusai" %% "shapeless" % "2.3.2"
)
