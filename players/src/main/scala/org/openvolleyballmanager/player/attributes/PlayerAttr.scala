package org.openvolleyballmanager.player.attributes

sealed class PlayerAttr(value: Int, max: Int = PlayerAttr.DEFAULT_ATTRIBUTE_MAX) {
  val coefficient: Double = value / max
}

// mental
case class Charisma(value: Int) extends PlayerAttr(value)

case class Creativity(value: Int) extends PlayerAttr(value)

case class Decisions(value: Int) extends PlayerAttr(value)

case class Determination(value: Int) extends PlayerAttr(value)

case class Experience(value: Int) extends PlayerAttr(value)

case class Intuition(value: Int) extends PlayerAttr(value)

// physical
case class Agility(value: Int) extends PlayerAttr(value)

case class Height(value: Int) extends PlayerAttr(value, PlayerAttr.MAX_HEIGHT_CM)

case class Jumping(value: Int) extends PlayerAttr(value)

case class Speed(value: Int) extends PlayerAttr(value)

case class Stamina(value: Int) extends PlayerAttr(value, PlayerAttr.MAX_STAMINA)

case class Strength(value: Int) extends PlayerAttr(value)

// technical
case class Block(value: Int) extends PlayerAttr(value)

case class Placing(value: Int) extends PlayerAttr(value)

case class Serve(value: Int) extends PlayerAttr(value)

case class Spike(value: Int) extends PlayerAttr(value)

case class Technique(value: Int) extends PlayerAttr(value)

object PlayerAttr {
  val MAX_HEIGHT_CM = 220
  val MAX_STAMINA = 100
  val DEFAULT_ATTRIBUTE_MAX = 20
}