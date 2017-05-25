package com.mfed.services.functions

import com.mfed.model.GameMapState
import scala.collection.JavaConversions._
/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 13:33.
  * mfedorowiat@gmail.com
  */

object GoFunction extends Function{
  override def produce(): (GameMapState) => GameMapState = {
    (gameMapState: GameMapState) => {
      val roboFuturePoint = gameMapState.produceNextPointByCurrentRotation()

      val diamonds = gameMapState.gameMap.diamonds.toList.filter(d => !d.equals(roboFuturePoint))

      gameMapState.copy(
        robotState = gameMapState.robotState.copy(point = roboFuturePoint),
        gameMap = gameMapState.gameMap.copy(diamonds = diamonds)
      )
    }
  }
}
