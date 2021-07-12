name         := "scalajs-probot"
organization := "laughedelic"
description  := "Scala.js facades for the Probot framework"

homepage := Some(url(s"https://github.com/laughedelic/${name.value}"))
scmInfo in ThisBuild := Some(ScmInfo(
  homepage.value.get,
  s"scm:git:git@github.com:laughedelic/${name.value}.git"
))

licenses := Seq("MPL-2.0" -> url("https://www.mozilla.org/en-US/MPL/2.0"))
developers := List(Developer(
  "laughedelic",
  "Alexey Alekhin",
  "laughedelic@gmail.com",
  url("https://github.com/laughedelic")
))

scalaVersion := "2.12.6"
scalacOptions ++= Seq(
  "-Yrangepos",
  "-P:scalajs:sjsDefinedByDefault",
  "-language:implicitConversions",
  "-deprecation",
  "-feature",
  "-Xlint"
)

enablePlugins(ScalaJSPlugin)

releaseEarlyWith := BintrayPublisher
releaseEarlyEnableSyncToMaven := false
releaseEarlyNoGpg := true

publishMavenStyle := true
bintrayReleaseOnPublish := !isSnapshot.value
bintrayPackageLabels := Seq("scalajs", "github", "probot", "facades")

ghreleaseAssets := Seq()

resolvers += Resolver.bintrayRepo("laughedelic", "maven")
libraryDependencies ++= Seq(
  "io.scalajs.npm" %%% "express" % "0.4.2",
  "io.scalajs" %%% "nodejs" % "0.4.2",
)

lazy val root = (project in file("."))
  .dependsOn(scalaJsOktokit)

lazy val scalaJsOktokit =
  ProjectRef(uri("https://github.com/laughedelic/scalajs-octokit.git#aa984a0d"), "scalajs-octokit")
