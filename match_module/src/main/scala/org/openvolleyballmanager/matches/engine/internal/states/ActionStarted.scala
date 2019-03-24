package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.engine.internal.ActionState
import org.openvolleyballmanager.matches.tactics._
import org.openvolleyballmanager.matches.{MatchState, PlayerRole}
import org.openvolleyballmanager.player.Player
import org.openvolleyballmanager.util.random.EventProbability

class ActionStarted(matchState: MatchState) extends ActionState {
  private val server: Player = {
    val serverTeam = matchState.servers()
    val serverTeamPlacing = matchState.placing(serverTeam)
    serverTeamPlacing.server()
  }

  private val serveParams: (ServeRisk, ServeType) = {
    val serverTeam = matchState.servers()
    val tactics = matchState.tactics(serverTeam)
    val serveRisk = tactics.serveRisk(server)
    val serveType = tactics.serveType(server)
    (serveRisk, serveType)
  }

  override def actionFinished(): Boolean = false

  override def execute(): ActionState = {
    val receiver = determineReceiver()
    serveFault()
      .orElse(serveAce())
      .orElse(goodReceive(receiver))
      .orElse(badReceive(receiver))
      .getOrElse(receiveError(receiver))
  }

  private def determineReceiver(): Player = {
    val serverTeam = matchState.servers()
    val serverTeamTactics = matchState.tactics(serverTeam)
    val serveDirection = serverTeamTactics.serveDirection(server)
    val receiverTeam = serverTeam.opponents()
    val receiverTeamPlacing = matchState.placing(receiverTeam)
    serveDirection match {
      case Libero => receiverTeamPlacing.player(PlayerRole.Libero)
      case WingSpiker1 => receiverTeamPlacing.player(PlayerRole.WingSpiker_1)
      case WingSpiker2 => receiverTeamPlacing.player(PlayerRole.WingSpiker_2)
      case Abridgment =>
    }
    ???
  }

  private def serveFault(): Option[ActionState] = eventWithProbability(faultProbability(), () => new ServeFail(server))

  private def serveAce(): Option[ActionState] = eventWithProbability(aceProbability(), () => new ServeAce(server))

  private def goodReceive(receiver: Player): Option[ActionState] = {
    val receiver = determineReceiver()
    eventWithProbability(goodReceiveProbability(receiver), () => new GoodReceive(receiver))
  }

  private def badReceive(receiver: Player): Option[ActionState] = {
    val receiver = determineReceiver()
    eventWithProbability(badReceiveProbability(receiver), () => new BadReceive(receiver))
  }

  private def receiveError(receiver: Player): ActionState = {
    val receiver = determineReceiver()
    new ReceiveError(receiver)
  }

  private def aceProbability(): Double = ???

  private def faultProbability(): Double = ???

  private def goodReceiveProbability(receiver: Player): Double = ???

  private def badReceiveProbability(receiver: Player): Double = ???

  private def eventWithProbability(probability: Double, nextStateSupplier: () => ActionState): Option[ActionState] =
    if (EventProbability.happens(probability))
      Some(nextStateSupplier())
    else
      None
}
