package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.MatchState
import org.openvolleyballmanager.matches.engine.internal.ActionState
import org.openvolleyballmanager.player.Player

class AttackError(matchState: MatchState, attacker: Player) extends ActionState {
  override def actionFinished(): Boolean = false

  override def execute(): ActionState = {
    val winners = matchState
      .team(attacker)
      .opponents()
    new ActionFinished(winners)
  }
}
