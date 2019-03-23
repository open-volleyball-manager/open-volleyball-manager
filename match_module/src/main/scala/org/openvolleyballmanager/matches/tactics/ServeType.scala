package org.openvolleyballmanager.matches.tactics

sealed trait ServeType

case object Technical extends ServeType
case object Mixed extends ServeType
case object Strength extends ServeType