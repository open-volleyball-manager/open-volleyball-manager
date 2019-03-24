package org.openvolleyballmanager.player.attributes

abstract class PlayerAttr(value: Int, max: Int = PlayerAttr.DEFAULT_ATTRIBUTE_MAX) {
  def coefficient: Double = value / max
}

object PlayerAttr {
  val DEFAULT_ATTRIBUTE_MAX = 20
}