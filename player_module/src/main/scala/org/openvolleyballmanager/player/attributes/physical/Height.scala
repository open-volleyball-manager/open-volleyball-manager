package org.openvolleyballmanager.player.attributes.physical

import org.openvolleyballmanager.player.attributes.PlayerAttr

class Height(value: Int) extends PlayerAttr(value, Height.MAX_HEIGHT_CM)

object Height {
  val MAX_HEIGHT_CM = 220
}
