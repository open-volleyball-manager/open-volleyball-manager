package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.engine.internal.ActionState

class BadReceive extends ActionState {
  override def actionFinished(): Boolean = false

  override def execute(): ActionState = ???
}