name := "contextual-play"

version := "1.0"

scalaVersion := "2.12.1"

val play = "com.typesafe.play" %% "play" % "2.6.0"
val contextual = "com.propensive" %% "contextual" % "1.0.1"
val shapeless = "com.chuusai" %% "shapeless" % "2.3.2"
val scalatest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"

libraryDependencies ++= Seq(
  play,
  contextual,
  shapeless,
  scalatest
)
