package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.engine.internal.ActionState
import org.openvolleyballmanager.matches.{MatchState, PlayerRole}
import org.openvolleyballmanager.player.Player
import org.openvolleyballmanager.util.random.EventProbability

import scala.util.Random

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

  private def errorProbability(): Double = {
    val technicalAttrs = setter.attributes.technical
    val mentalAttrs = setter.attributes.mental
    val technique = technicalAttrs.technique
    val decisions = mentalAttrs.decisions
    val experience = mentalAttrs.experience
    0.1 / (technique.coefficient * decisions.coefficient * experience.coefficient)
  }

  private def trickProbability(): Double = {
    val technicalAttrs = setter.attributes.technical
    val mentalAttrs = setter.attributes.mental
    val creativity = mentalAttrs.creativity
    val determination = mentalAttrs.determination
    val technique = technicalAttrs.technique
    val charisma = mentalAttrs.charisma
    creativity.coefficient * determination.coefficient * technique.coefficient * charisma.coefficient
  }

  private def goodSettingProbability(): Double = {
    val technicalAttrs = setter.attributes.technical
    val mentalAttrs = setter.attributes.mental
    val technique = technicalAttrs.technique
    val creativity = mentalAttrs.creativity
    val decisions = mentalAttrs.decisions
    val intuition = mentalAttrs.intuition
    val experience = mentalAttrs.experience
    technique.coefficient * decisions.coefficient + creativity.coefficient * intuition.coefficient * experience.coefficient
  }

  private def determineAttacker(): Player = {
    val team = matchState.team(setter)
    val placing = matchState.placing(team)
    val attackers = List(
      placing.player(PlayerRole.Opposite),
      placing.player(PlayerRole.WingSpiker_1),
      placing.player(PlayerRole.WingSpiker_2),
      placing.player(PlayerRole.MiddleBlocker_1)
    )
    attackers(new Random().nextInt(attackers.size))
  }

  private def eventWithProbability(probability: Double, nextStateSupplier: () => ActionState): Option[ActionState] =
    if (EventProbability.happens(probability))
      Some(nextStateSupplier())
    else
      None
}
