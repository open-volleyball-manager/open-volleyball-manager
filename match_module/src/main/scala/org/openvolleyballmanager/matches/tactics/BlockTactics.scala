package org.openvolleyballmanager.matches.tactics

sealed trait BlockTactics

case object Active
case object Passive
case object Changing