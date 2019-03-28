package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.engine.MatchEngineConstants
import org.openvolleyballmanager.matches.engine.internal.ActionState
import org.openvolleyballmanager.matches.tactics._
import org.openvolleyballmanager.matches.{MatchState, PlayerRole, TeamPlacing}
import org.openvolleyballmanager.player.Player
import org.openvolleyballmanager.util.random.EventProbability

class Serve(matchState: MatchState) extends ActionState {
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
      case Abridgment => determineReceiverForAbridgment(receiverTeamPlacing, serveDirection)
    }
  }

  private def determineReceiverForAbridgment(receiverTeamPlacing: TeamPlacing, serveDirection: ServeDirection): Player = ???

  private def aceProbability(): Double = {
    val serveRisk = serveParams._1
    val riskCoefficient = serveRisk match {
      case Safe => MatchEngineConstants.ACE_SERVE_RISK_SAFE_COEFFICIENT
      case Normal => MatchEngineConstants.ACE_SERVE_RISK_NORMAL_COEFFICIENT
      case Risky => MatchEngineConstants.ACE_SERVE_RISK_RISKY_COEFFICIENT
    }
    val serverAttrs = server.attributes
    val technicalAttrs = serverAttrs.technical
    val physicalAttrs = serverAttrs.physical
    val technique = technicalAttrs.technique
    val strength = physicalAttrs.strength
    val jumping = physicalAttrs.jumping
    val serveType = serveParams._2
    val tacticsCoefficient = serveType match {
      case TechnicalServe => technique.coefficient
      case MixedServe => (technique.coefficient + (strength.coefficient + jumping.coefficient) / 2) / 2
      case StrengthServe => (strength.coefficient + jumping.coefficient) / 2
    }
    val shapeCoefficient = 1.0 // TODO get from shape
    shapeCoefficient * technicalAttrs.serve.coefficient * riskCoefficient * tacticsCoefficient
  }

  private def faultProbability(): Double = (1 - aceProbability()) / 2

  private def goodReceiveProbability(receiver: Player): Double = ???

  private def badReceiveProbability(receiver: Player): Double = 0.9 * (1 - goodReceiveProbability(receiver))

  private def eventWithProbability(probability: Double, nextStateSupplier: () => ActionState): Option[ActionState] =
    if (EventProbability.happens(probability))
      Some(nextStateSupplier())
    else
      None
}
