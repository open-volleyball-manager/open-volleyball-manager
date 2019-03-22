package org.openvolleyballmanager.`match`.engine.internal

trait ActionState {
  def actionFinished(): Boolean
  def execute(): ActionState
}
