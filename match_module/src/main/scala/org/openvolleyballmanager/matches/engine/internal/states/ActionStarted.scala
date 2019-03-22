package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.Placing
import org.openvolleyballmanager.matches.engine.internal.ActionState

class ActionStarted(hostsPlacing: Placing, guestsPlacing: Placing, hostsServe: Boolean) extends ActionState {
  override def actionFinished(): Boolean = false

  override def execute(): ActionState = ???
}
