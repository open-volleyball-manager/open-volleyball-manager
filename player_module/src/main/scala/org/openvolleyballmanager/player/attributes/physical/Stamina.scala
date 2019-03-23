package org.openvolleyballmanager.player.attributes.physical

import org.openvolleyballmanager.player.attributes.PlayerAttr

class Stamina(value: Int) extends PlayerAttr(value, Stamina.MAX_STAMINA)

object Stamina {
  val MAX_STAMINA = 100
}
