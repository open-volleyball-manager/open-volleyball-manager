package org.openvolleyballmanager.matches

import org.openvolleyballmanager.matches.tactics.MatchTactics

trait MatchState {
  def hostsPlacing(): Placing = ???

  def guestsPlacing(): Placing = ???

  def hostsServe(): Boolean = ???

  def hostsTactics(): MatchTactics = ???

  def guestsTactics(): MatchTactics = ???
}
