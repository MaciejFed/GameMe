package com.mfed.services.impl

import com.mfed.model.GameMapState
import com.mfed.services.FunctionService
import org.springframework.stereotype.Component

import scala.util.matching.Regex

/**
  * Created by Maciej Fedorowiat 
  * on 06/02/2017 14:40.
  * mfedorowiat@gmail.com
  */
@Component
class FunctionServiceImpl extends FunctionService{
  val multiGoPattern: Regex = "(go\\([0-9]\\);)".r

  override def produceFunctionsFromCode(code: List[String]): List[(GameMapState) => GameMapState] = {
    code.map {
      case multiGoPattern(c) => composeFunction(functionGo,  Character.getNumericValue(c.charAt(3)))
      case "go();" => functionGo
      case "rotateRight();" => rotateRight
      case _ => (gameMapState: GameMapState) => gameMapState
    }
  }

  def composeFunction(function: GameMapState => GameMapState, times: Int) = {
      (1 to times)
      .map(_ => functionGo)
      .foldLeft((gameMapState: GameMapState) => gameMapState)((a, b) => b.compose(a))
  }

  def functionGo = {
    (gameMapState: GameMapState) => {
      val roboPoint = gameMapState.robotState.point
      val roboFuturePoint = (roboPoint._1 + 1, roboPoint._2)

      if(!gameMapState.gameMap.obstacles.contains(roboFuturePoint)  &&  roboFuturePoint._1 < gameMapState.gameMap.width)
        gameMapState.copy(robotState = gameMapState.robotState.copy(point = roboFuturePoint))
      else
        gameMapState
    }
  }

  def rotateRight = {
    (gameMapState: GameMapState) => {
      val roboRotation = gameMapState.robotState.rotation

      gameMapState.copy(robotState = gameMapState.robotState.copy(rotation = (roboRotation + 1) % 4))
    }
  }
}


