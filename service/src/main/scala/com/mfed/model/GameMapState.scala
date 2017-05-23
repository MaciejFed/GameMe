package com.mfed.model

/**
  * Created by Maciej Fedorowiat 
  * on 03/02/2017 13:58.
  * mfedorowiat@gmail.com
  */
case class GameMapState(gameMap: GameMap, robotState: RobotState, variables: Map[String, Int]) {
  def produceNextPointByCurrentRotation(): (Int, Int) = {
    val roboPoint = robotState.point

    robotState.rotation % 4 match {
      case 0 => if(0 <= roboPoint._2 - 1) (roboPoint._1, roboPoint._2 - 1) else roboPoint
      case 1 => if(gameMap.width > roboPoint._1 + 1) (roboPoint._1 + 1, roboPoint._2) else roboPoint
      case 2 => if(gameMap.height > roboPoint._2 + 1) (roboPoint._1, roboPoint._2 + 1) else roboPoint
      case 3 => if(0 <= roboPoint._1 -1) (roboPoint._1 - 1, roboPoint._2) else roboPoint
      case _ => (0, 0)
    }
  }
}
