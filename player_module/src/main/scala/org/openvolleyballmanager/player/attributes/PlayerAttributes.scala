package org.openvolleyballmanager.player.attributes

import org.openvolleyballmanager.player.attributes.mental.MentalAttributes
import org.openvolleyballmanager.player.attributes.physical.PhysicalAttributes
import org.openvolleyballmanager.player.attributes.technical.TechnicalAttributes

case class PlayerAttributes
(
  mental: MentalAttributes,
  physical: PhysicalAttributes,
  technical: TechnicalAttributes
)
