package org.openvolleyballmanager.matches

import org.openvolleyballmanager.matches.PlayerRole.PlayerRole
import org.openvolleyballmanager.player.Player

class TeamPlacing(players: Map[PlayerRole, Player]) {
  def server(): Player = ???

  def player(role: PlayerRole): Player = players(role)
}

