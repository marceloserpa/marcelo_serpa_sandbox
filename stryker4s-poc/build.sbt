ThisBuild / scalaVersion := "2.13.18"

lazy val root = (project in file("."))
  .settings(
    name := "stryker4s-poc",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test
  )
