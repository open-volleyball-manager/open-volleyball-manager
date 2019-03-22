package org.openvolleyballmanager.`match`.engine.internal.states

import org.openvolleyballmanager.`match`.engine.internal.ActionState

class ActionStarted extends ActionState {
  override def actionFinished(): Boolean = false
  override def execute(): ActionState = ???
}
