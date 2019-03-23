package org.openvolleyballmanager.matches.tactics

sealed trait ServeDirection

case object AnyServeDirection extends ServeDirection
case object Libero extends ServeDirection
case object WingSpiker1 extends ServeDirection
case object WingSpiker2 extends ServeDirection
case object Abridgment extends ServeDirection