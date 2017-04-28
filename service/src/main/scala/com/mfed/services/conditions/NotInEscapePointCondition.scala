package com.mfed.services.conditions
import com.mfed.model.GameMapState

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 15:10.
  * mfedorowiat@gmail.com
  */
object NotInEscapePointCondition extends Condition{
  val name = "notInEscapePoint()"

  override def produce(): (GameMapState) => Boolean = {
    (gameMapState: GameMapState) => {
      !gameMapState.gameMap.endPoint.equals(gameMapState.robotState.point)
    }
  }
}
