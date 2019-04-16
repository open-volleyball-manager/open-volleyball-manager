package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.MatchState
import org.openvolleyballmanager.matches.engine.internal.ActionState
import org.openvolleyballmanager.player.Player
import org.openvolleyballmanager.util.random.EventProbability

class Set(setter: Player, matchState: MatchState) extends ActionState {
  override def actionFinished(): Boolean = false

  override def execute(): ActionState = {
    settingError()
      .orElse(setterTrick())
      .orElse(goodSetting())
      .getOrElse(badSetting())
  }

  private def settingError(): Option[ActionState] = eventWithProbability(
    errorProbability(),
    () => new SettingError(setter, matchState))

  private def setterTrick(): Option[ActionState] = eventWithProbability(
    trickProbability(),
    () => new SetterTrick(setter, matchState)
  )

  private def goodSetting(): Option[ActionState] = eventWithProbability(
    goodSettingProbability(),
    () => new Attack(determineAttacker(), matchState)
  )

  private def badSetting(): ActionState =
    new SituationalAttack(determineAttacker(), matchState)

  private def errorProbability(): Double = ???

  private def trickProbability(): Double = ???

  private def goodSettingProbability(): Double = ???

  private def determineAttacker(): Player = ???

  private def eventWithProbability(probability: Double, nextStateSupplier: () => ActionState): Option[ActionState] =
    if (EventProbability.happens(probability))
      Some(nextStateSupplier())
    else
      None
}
