package org.openvolleyballmanager.matches.tactics

sealed trait BlockDirection

case object Any extends BlockDirection
case object LeftWing extends BlockDirection
case object RightWing extends BlockDirection
case object Center extends BlockDirection