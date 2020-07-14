package org.openvolleyballmanager.matches.tactics

sealed trait AttackType

case object Technical extends AttackType
case object Mixed extends AttackType
case object Strength extends AttackType