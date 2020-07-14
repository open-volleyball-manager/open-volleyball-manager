package org.openvolleyballmanager.matches.engine

import org.openvolleyballmanager.matches.{Match, MatchResult}

trait MatchEngine {
  def playMatch(game: Match): MatchResult
}
