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
  val multiRotatePattern: Regex = "(rotateRight\\([0-9]\\);)".r

  override def produceFunctionsFromCode(code: List[String]): List[(GameMapState) => GameMapState] = {
    code.map {
      case multiGoPattern(c) => composeFunction(functionGo,  Character.getNumericValue(c.charAt(3)))
      case multiRotatePattern(c) => composeFunction(rotateRight,  Character.getNumericValue(c.charAt(12)))
      case "go();" => functionGo
      case "rotateRight();" => rotateRight
      case _ => (gameMapState: GameMapState) => gameMapState
    }
  }

  def composeFunction(function: GameMapState => GameMapState, times: Int) = {
      (1 to times)
      .map(_ => function)
      .foldLeft((gameMapState: GameMapState) => gameMapState)((a, b) => b.compose(a))
  }

  def functionGo = {
    (gameMapState: GameMapState) => {
      val roboFuturePoint = produceChangeFromRotation(gameMapState)

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

  def produceChangeFromRotation(gameMapState: GameMapState): (Int, Int) = {
    val roboPoint = gameMapState.robotState.point

    gameMapState.robotState.rotation match {
      case 0 => (roboPoint._1, roboPoint._2 - 1)
      case 1 => (roboPoint._1 + 1, roboPoint._2)
      case 2 => (roboPoint._1, roboPoint._2 + 1)
      case 3 => (roboPoint._1 - 1, roboPoint._2)
      case _ => (0, 0)
    }
  }
}


