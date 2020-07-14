package org.openvolleyballmanager.util.random

import scala.util.Random

object EventProbability {
  def happens(probability: Double): Boolean =
    next <= probability

  private def next: Double = {
    val random = new Random()
    random.nextDouble()
  }
}
