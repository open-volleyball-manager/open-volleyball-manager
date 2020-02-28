package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.PlayerRole.PlayerRole
import org.openvolleyballmanager.matches.engine.internal.ActionState
import org.openvolleyballmanager.matches.{MatchState, PlayerRole, TeamPlacing}
import org.openvolleyballmanager.player.Player

import scala.util.Random

class BadReceive(receiver: Player, matchState: MatchState) extends ActionState {
  override def actionFinished(): Boolean = false

  override def execute(): ActionState = {
    val setter = determineSetter()
    new SituationalSet(setter, matchState)
  }

  private def determineSetter(): Player = {
    val receivingTeam = matchState.team(receiver)
    val receivingTeamPlacing = matchState.placing(receivingTeam)
    randomPick(receivingTeamPlacing)
  }

  private def randomPick(receivingTeamPlacing: TeamPlacing): Player = {
    val random = new Random()

    def pickPlayer(playerRole: PlayerRole, rolesProbabilities: Map[PlayerRole, Double]): Option[Player] = {
      if (random.nextDouble() > rolesProbabilities.getOrElse(playerRole, 1)) {
        None
      } else {
        Some(receivingTeamPlacing.player(playerRole))
          .filter(player => player != receiver)
      }
    }

    val probabilities = Map(
      PlayerRole.Setter -> 0.8,
      PlayerRole.MiddleBlocker_1 -> 0.25,
      PlayerRole.MiddleBlocker_2 -> 0.25,
      PlayerRole.Libero -> 0.25,
      PlayerRole.WingSpiker_1 -> 0.25,
      PlayerRole.WingSpiker_2 -> 0.25,
      PlayerRole.Opposite -> 0.25
    )

    probabilities
      .keys
      .toStream
      .flatMap(role => pickPlayer(role, probabilities))
      .filter(player => player != receiver)
      .find(_ => true)
      .getOrElse(nonReceiverRole(receivingTeamPlacing))
  }

  private def nonReceiverRole(receivingTeamPlacing: TeamPlacing): PlayerRole = {
    PlayerRole.values
      .filter(role => receivingTeamPlacing.player(role) != receiver)
      .firstKey
  }
}
