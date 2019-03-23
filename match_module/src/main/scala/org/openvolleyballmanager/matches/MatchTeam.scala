package org.openvolleyballmanager.matches

sealed trait MatchTeam {
  def opponents(): MatchTeam
}

case object Hosts extends MatchTeam {
  override def opponents(): MatchTeam = Guests
}

case object Guests extends MatchTeam {
  override def opponents(): MatchTeam = Hosts
}
