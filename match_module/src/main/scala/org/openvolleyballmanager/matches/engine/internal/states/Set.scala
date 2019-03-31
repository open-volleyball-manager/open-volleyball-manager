package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.MatchState
import org.openvolleyballmanager.matches.engine.internal.ActionState
import org.openvolleyballmanager.player.Player

class Set(setter: Player, matchState: MatchState) extends ActionState {
  override def actionFinished(): Boolean = false

  override def execute(): ActionState = {
    settingError()
      .orElse(setterTrick())
      .orElse(goodSetting())
      .getOrElse(badSetting())
  }

  private def settingError(): Option[ActionState] = ???

  private def setterTrick(): Option[ActionState] = ???

  private def goodSetting(): Option[ActionState] = ???

  private def badSetting(): ActionState = ???
}
