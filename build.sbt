name := "open-volleyball-manager"
version := "0.1"
organization in ThisBuild := "org.openvolleyballmanager"
scalaVersion in ThisBuild := "2.12.8"

lazy val root = project
  .in(file("."))
  .aggregate(
    common,
    players,
    teams,
    matches
  )

lazy val common = project
  .withId("ovm-common")
  .settings(
    name := "ovm-common"
  )

lazy val players = project
  .withId("ovm-players")
  .settings(
    name := "ovm-player"
  )
  .dependsOn(
    common
  )

lazy val teams = project
  .withId("ovm-teams")
  .settings(
    name := "ovm-team"
  )
  .dependsOn(
    common,
    players
  )

lazy val matches = project
  .withId("ovm-matches")
  .settings(
    name := "ovm-match"
  )
  .dependsOn(
    common,
    players,
    teams
  )
