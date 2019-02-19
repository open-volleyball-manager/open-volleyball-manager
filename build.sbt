name := "open-volleyball-manager"
version := "0.1"
organization in ThisBuild := "org.openvolleyballmanager"
scalaVersion in ThisBuild := "2.12.8"

lazy val root = project
  .in(file("."))
  .aggregate(
    player_module
  )

lazy val player_module = project
  .settings(
    name := "player_module"
  )

lazy val team_module = project
  .settings(
    name := "team_module"
  )
  .dependsOn(
    player_module
  )

lazy val match_module = project
  .settings(
    name := "match_module"
  )
  .dependsOn(
    player_module,
    team_module
  )
