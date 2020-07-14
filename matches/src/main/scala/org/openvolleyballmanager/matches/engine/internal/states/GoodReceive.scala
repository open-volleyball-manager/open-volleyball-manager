package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.engine.internal.ActionState
import org.openvolleyballmanager.matches.{MatchState, PlayerRole}
import org.openvolleyballmanager.player.Player

class GoodReceive(receiver: Player, matchState: MatchState) extends ActionState {
  override def actionFinished(): Boolean = false

  override def execute(): ActionState = {
    val receiverTeam = matchState.team(receiver)
    val receiverTeamPlacing = matchState.placing(receiverTeam)
    val setter = receiverTeamPlacing.player(PlayerRole.Setter)
    new Set(setter, matchState)
  }
}
