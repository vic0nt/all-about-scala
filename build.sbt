name := "all-about-scala"

val scalazVersion = "7.2.24"
val circeVersion = "0.10.0"

lazy val commonSettings = Seq(
  organization := "com.example",
  version := "0.1",
  scalaVersion := "2.12.4",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "1.2.0",
    "org.scalaz" %% "scalaz-core" % scalazVersion,
    "com.kailuowang" %% "henkan-optional" % "0.6.1",
    "org.scalactic" %% "scalactic" % "3.0.4",
    "org.scalatest" %% "scalatest" % "3.0.4" % "test"
  ),
  libraryDependencies ++= Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % circeVersion)
)

lazy val root = project in file(".")

lazy val misc = project.settings(
  commonSettings
)
lazy val euler = project.settings(
  commonSettings
)
lazy val hackerrank = project.settings(
  commonSettings
)
lazy val scalaFp = (project in file("scala-fp")).settings(
  commonSettings
)

scalacOptions += "-Ypartial-unification"

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)
