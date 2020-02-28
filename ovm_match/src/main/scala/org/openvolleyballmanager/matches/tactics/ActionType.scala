package org.openvolleyballmanager.matches.tactics

sealed trait ActionType

case object Combinative extends ActionType
case object Center extends ActionType
case object LeftWing extends ActionType
case object RightWing extends ActionType
case object ToSpiker extends ActionType
case object ToWingSpiker1 extends ActionType
case object ToWingSpiker2 extends ActionType
