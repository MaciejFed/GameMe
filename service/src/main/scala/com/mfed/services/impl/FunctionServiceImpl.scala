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
  val multiGoPattern: Regex = "(go\\(.+\\);)".r
  val multiRotatePattern: Regex = "(rotateRight\\(.+\\);)".r
  val variableDefinitionPattern: Regex = "(def \\D.+\\s*=\\s*\\d+;)".r

  override def produceFunctionsFromCode(code: List[String]): List[(GameMapState) => GameMapState] = {
    code.map {
      case multiGoPattern(c) => composeWithValueName(functionGo,  readValueName(c))
      case multiRotatePattern(c) => composeWithValueName(rotateRight,  readValueName(c))
      case variableDefinitionPattern(c) => saveVariable(c)
      case "go();" => functionGo
      case "rotateRight();" => rotateRight
      case _ => (gameMapState: GameMapState) => gameMapState
    }
  }

  def readValueName(function: String): String = function.substring(function.indexOf('(') + 1, function.lastIndexOf(')'))

  def composeWithValueName(function: GameMapState => GameMapState, valueName: String): GameMapState => GameMapState = {
      if(valueName.matches("\\d+$"))
      composeFunction(function, Integer.parseInt(valueName))
    else
      composeFunction(function, valueName)
  }

  def composeFunction(function: GameMapState => GameMapState, valueName: String): GameMapState => GameMapState = {
    (gameMapState: GameMapState) => {
        composeFunction(function, gameMapState.variables(valueName))(gameMapState)
    }
  }

  def composeFunction(function: GameMapState => GameMapState, times: Int): GameMapState => GameMapState  = {
      (1 to times)
      .map(_ => function)
      .foldLeft((gameMapState: GameMapState) => gameMapState)((a, b) => b.compose(a))
  }

  def saveVariable(definition: String) = {
    (gameMapState: GameMapState) => {
      val name = definition.substring(3, definition.indexOf('=')).replaceAll("\\s", "")
      val value = Integer.parseInt(definition.substring(definition.indexOf('=') + 1, definition.length - 1).replaceAll("\\s", ""))

      gameMapState.copy(variables = gameMapState.variables + (name -> value))
    }
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


