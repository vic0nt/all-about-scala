name := "all-about-scala"

lazy val commonSettings = Seq(
  organization := "com.example",
  version := "0.1",
  scalaVersion := "2.12.4",
  libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % "3.0.4",
    "org.scalatest" %% "scalatest" % "3.0.4" % "test"
  )
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
