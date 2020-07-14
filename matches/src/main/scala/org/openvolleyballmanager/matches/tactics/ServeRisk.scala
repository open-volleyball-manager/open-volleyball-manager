package org.openvolleyballmanager.matches.tactics

sealed trait ServeRisk

case object Safe extends ServeRisk
case object Normal extends ServeRisk
case object Risky extends ServeRisk