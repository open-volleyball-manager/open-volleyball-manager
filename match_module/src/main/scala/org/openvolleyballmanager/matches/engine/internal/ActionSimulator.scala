package org.openvolleyballmanager.matches.engine.internal

import org.openvolleyballmanager.matches.MatchState
import org.openvolleyballmanager.matches.engine.internal.states.ActionStarted

class ActionSimulator {

  def playAction(matchState: MatchState): MatchState = {
    val initialState = new ActionStarted(matchState)
    val actionStates = play(initialState, List())
    updateMatchState(matchState, actionStates)
  }

  private def updateMatchState(currentState: MatchState, actionStates: List[ActionState]): MatchState = ???

  private def play(currentState: ActionState, prevStates: List[ActionState]): List[ActionState] =
    if (currentState.actionFinished())
      currentState :: prevStates
    else
      play(currentState.execute(), currentState :: prevStates)
}
