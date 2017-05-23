package com.mfed.services.functions

import com.mfed.model.GameMapState

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 13:34.
  * mfedorowiat@gmail.com
  */
object RotateRightFunction extends Function{
  override def produce(): GameMapState => GameMapState = {
    (gameMapState: GameMapState) => {
      val roboRotation = gameMapState.robotState.rotation

      gameMapState.copy(robotState = gameMapState.robotState.copy(rotation = roboRotation + 1))
    }
  }
}

