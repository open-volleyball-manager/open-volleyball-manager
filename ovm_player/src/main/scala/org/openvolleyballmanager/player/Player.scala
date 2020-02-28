package org.openvolleyballmanager.player

import java.time.LocalDate

import org.openvolleyballmanager.country.Country
import org.openvolleyballmanager.player.attributes.PlayerAttributes

case class Player
(
  firstName: String,
  lastName: String,
  birthDate: LocalDate,
  nationality: Country,
  attributes: PlayerAttributes
)
