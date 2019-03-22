package org.openvolleyballmanager.matches

trait MatchState {
  def hostsPlacing(): Placing = ???

  def guestsPlacing(): Placing = ???

  def hostsServe(): Boolean = ???
}
