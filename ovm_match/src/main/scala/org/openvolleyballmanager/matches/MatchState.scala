package org.openvolleyballmanager.matches

import org.openvolleyballmanager.matches.tactics.MatchTactics
import org.openvolleyballmanager.player.{Player, PlayerShape}

trait MatchState {
  def placing(team: MatchTeam): TeamPlacing = ???

  def servers(): MatchTeam = ???

  def tactics(team: MatchTeam): MatchTactics = ???

  def shape(player: Player): PlayerShape = ???

  def team(player: Player): MatchTeam = ???
}
