package org.openvolleyballmanager.matches.engine.internal.states

import org.openvolleyballmanager.matches.{MatchState, Placing}
import org.openvolleyballmanager.matches.engine.internal.ActionState
import org.openvolleyballmanager.player.Player

class ActionStarted(matchState: MatchState) extends ActionState {
  private val server: Player =
    if (matchState.hostsServe())
      matchState.hostsPlacing().server()
    else
      matchState.guestsPlacing().server()

  override def actionFinished(): Boolean = false

  override def execute(): ActionState = {
    val receiver = determineReceiver()
    serveFail()
      .orElse(goodReceive(receiver))
      .orElse(badReceive(receiver))
      .orElse(receiveError(receiver))
      .getOrElse(serveAce())
  }

  private def determineReceiver(): Player = ???

  private def serveFail(): Option[ActionState] = ???

  private def goodReceive(receiver: Player): Option[ActionState] = ???

  private def badReceive(receiver: Player): Option[ActionState] = ???

  private def receiveError(receiver: Player): Option[ActionState] = ???

  private def serveAce(): ActionState = new ServeAce(server)
}
