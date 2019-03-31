package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.MatchState
import org.openvolleyballmanager.matches.engine.internal.ActionState
import org.openvolleyballmanager.player.Player

class BadReceive(receiver: Player, matchState: MatchState) extends ActionState {
  override def actionFinished(): Boolean = false

  override def execute(): ActionState = {
    val setter = determineSetter()
    new SituationalSet(setter, matchState)
  }

  private def determineSetter(): Player = {
    ???
  }
}
