package org.openvolleyballmanager.matches

import org.openvolleyballmanager.matches.tactics.MatchTactics

trait MatchState {
  def placing(team: MatchTeam): TeamPlacing = ???

  def servers(): MatchTeam = ???

  def tactics(team: MatchTeam): MatchTactics = ???
}
