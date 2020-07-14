package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.MatchTeam
import org.openvolleyballmanager.matches.engine.internal.ActionState

class ActionFinished(winners: MatchTeam) extends ActionState {
  override def actionFinished(): Boolean = true

  override def execute(): ActionState = this
}
