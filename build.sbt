name := "open-volleyball-manager"
version := "0.1"
organization in ThisBuild := "org.openvolleyballmanager"
scalaVersion in ThisBuild := "2.12.8"

lazy val root = project
  .in(file("."))
  .aggregate(
    ovm_common,
    ovm_player,
    ovm_team,
    ovm_match
  )

lazy val ovm_common = project
  .settings(
    name := "ovm-common"
  )

lazy val ovm_player = project
  .settings(
    name := "ovm-player"
  )
  .dependsOn(
    ovm_common
  )

lazy val ovm_team = project
  .settings(
    name := "ovm-team"
  )
  .dependsOn(
    ovm_common,
    ovm_player
  )

lazy val ovm_match = project
  .settings(
    name := "ovm-match"
  )
  .dependsOn(
    ovm_common,
    ovm_player,
    ovm_team
  )
