package org.openvolleyballmanager.matches.tactics

sealed trait ServeType

case object TechnicalServe extends ServeType
case object MixedServe extends ServeType
case object StrengthServe extends ServeType