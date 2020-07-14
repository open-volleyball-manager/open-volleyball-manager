package org.openvolleyballmanager.matches.tactics

import org.openvolleyballmanager.player.Player

class MatchTactics {
  def actionType(): ActionType = ???

  def blockTactics(): BlockTactics = ???

  def blockDirection(): BlockDirection = ???

  def serveDirection(player: Player): ServeDirection = ???

  def serveType(player: Player): ServeType = ???

  def serveRisk(player: Player): ServeRisk = ???

  def attackType(player: Player): AttackType = ???
}
