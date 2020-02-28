package org.openvolleyballmanager.player.attributes

case class PlayerAttributes(mental: MentalAttributes, physical: PhysicalAttributes, technical: TechnicalAttributes)

case class MentalAttributes
(
  decisions: Decisions,
  determination: Determination,
  charisma: Charisma,
  intuition: Intuition,
  creativity: Creativity,
  experience: Experience
)

case class PhysicalAttributes
(
  strength: Strength,
  jumping: Jumping,
  speed: Speed,
  stamina: Stamina,
  agility: Agility
)

case class TechnicalAttributes
(
  spike: Spike,
  block: Block,
  technique: Technique,
  serve: Serve,
  placing: Placing
)