package org.openvolleyballmanager.matches.engine.internal

trait ActionState {
  def actionFinished(): Boolean
  def execute(): ActionState
}
