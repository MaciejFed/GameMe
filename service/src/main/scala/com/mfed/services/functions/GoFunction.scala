package com.mfed.services.functions

import com.mfed.model.GameMapState

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 13:33.
  * mfedorowiat@gmail.com
  */

object GoFunction extends Function{
  override def produce(): (GameMapState) => GameMapState = {
    (gameMapState: GameMapState) => {
      val roboFuturePoint = gameMapState.produceNextPointByCurrentRotation()

      if(!gameMapState.gameMap.obstacles.contains(roboFuturePoint))
        gameMapState.copy(robotState = gameMapState.robotState.copy(point = roboFuturePoint))
      else
        gameMapState
    }
  }
}
