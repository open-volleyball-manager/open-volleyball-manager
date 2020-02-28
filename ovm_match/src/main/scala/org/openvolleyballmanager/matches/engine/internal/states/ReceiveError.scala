package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.MatchState
import org.openvolleyballmanager.matches.engine.internal.ActionState
import org.openvolleyballmanager.player.Player

class ReceiveError(receiver: Player, matchState: MatchState) extends ActionState {
  override def actionFinished(): Boolean = false

  override def execute(): ActionState = {
    val winners = matchState
      .team(receiver)
      .opponents()
    new ActionFinished(winners)
  }
}
