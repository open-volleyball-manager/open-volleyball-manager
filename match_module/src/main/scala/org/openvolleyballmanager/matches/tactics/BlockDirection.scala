package org.openvolleyballmanager.matches.tactics

sealed trait BlockDirection

case object BlockAny extends BlockDirection
case object BlockLeftWing extends BlockDirection
case object BlockRightWing extends BlockDirection
case object BlockCenter extends BlockDirection