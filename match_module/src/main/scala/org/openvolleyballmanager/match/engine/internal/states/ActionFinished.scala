package org.openvolleyballmanager.`match`.engine.internal.states

import org.openvolleyballmanager.`match`.engine.internal.ActionState

class ActionFinished extends ActionState {
  override def actionFinished(): Boolean = true

  override def execute(): ActionState = ???
}
