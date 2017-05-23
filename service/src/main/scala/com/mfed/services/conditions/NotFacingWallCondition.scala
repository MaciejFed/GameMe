package com.mfed.services.conditions

import com.mfed.model.GameMapState

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 13:46.
  * mfedorowiat@gmail.com
  */
object NotFacingWallCondition extends Condition{
  val name = "notFacingWall()"

  override def produce() = {
    (gameMapState: GameMapState) => {
      val nextPoint = gameMapState.produceNextPointByCurrentRotation()
      gameMapState.gameMap.inValidPlace(nextPoint)
    }
  }
}
