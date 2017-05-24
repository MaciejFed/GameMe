package com.mfed.services.conditions

import com.mfed.model.GameMapState

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 13:46.
  * mfedorowiat@gmail.com
  */
object FacingWallCondition extends Condition{
  val name = "((?=.*?FacingWall)^.*$)".r

  override def produce() = {
    (gameMapState: GameMapState) => {
      val currentPoint = gameMapState.robotState.point
      val nextPoint = gameMapState.produceNextPointByCurrentRotation()

      currentPoint.equals(nextPoint)
    }
  }
}
