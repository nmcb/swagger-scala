import bintray.Keys._

lazy val commonSettings = Seq(
  version in ThisBuild := "0.0.1-SNAPSHOT",
  organization in ThisBuild := "org.zalando"
)

val jacksonDependencies = Seq(
  "com.fasterxml.jackson.core" % "jackson-core",
  "com.fasterxml.jackson.core" % "jackson-annotations",
  "com.fasterxml.jackson.core" % "jackson-databind",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jdk8",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310",
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml",
  "com.fasterxml.jackson.module" %% "jackson-module-scala"
).map(_ % "2.5.3")

lazy val root = {
  project.in(file("."))
  .settings(commonSettings ++ bintrayPublishSettings: _*)
  .settings(
      scalaVersion := "2.11.7",
      name := "swagger-scala",
      description := "A Swagger 2.0 model with Jackson file parsing",
      scalacOptions ++= Seq("-deprecation", "-feature"),
      licenses +=("MIT", url("http://opensource.org/licenses/MIT")),
      publishMavenStyle := false,
      repository in bintray := "brandsolutions",
      bintrayOrganization in bintray := Some("zalando"),
      initialCommands in console := "import org.zalando.speccer._",
      resolvers += Resolver.sonatypeRepo("snapshots"),
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "2.2.4" % "test",
        "junit" % "junit" % "4.12" % "test"
      ) ++ jacksonDependencies
    )
}