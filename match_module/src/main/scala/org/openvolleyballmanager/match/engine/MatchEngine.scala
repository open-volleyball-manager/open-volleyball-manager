package org.openvolleyballmanager.`match`.engine

import org.openvolleyballmanager.`match`.{Match, MatchResult}

trait MatchEngine {
  def playMatch(game: Match): MatchResult
}
