package com.mfed.services.functions

import com.mfed.model.GameMapState
import org.springframework.stereotype.Component

import scala.util.matching.Regex

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 13:20.
  * mfedorowiat@gmail.com
  */
@Component
class FunctionProducer {
  private val multiGoPattern: Regex = "(go\\(.+\\);)".r
  private val multiRotatePattern: Regex = "(rotateRight\\(.+\\);)".r
  private val variableDefinitionPattern: Regex = "(def \\D.+\\s*=\\s*\\d+;)".r

  def produceFunctionsFromCode(code: List[String]): List[(GameMapState) => List[(List[GameMapState], String)]] = {
    code.map {
      case multiGoPattern(c) => composeWithValueName(GoFunction.produce(),  readValueName(c), c)
      case multiRotatePattern(c) => composeWithValueName(RotateRightFunction.produce(),  readValueName(c), c)
      case variableDefinitionPattern(c) => asListResult(SaveVariableFunction.produce(c), c)
      case "go();" => asListResult(GoFunction.produce(), "go();")
      case "rotateRight();" => asListResult(RotateRightFunction.produce(), "go();")
      case _ => (gameMapState: GameMapState) => List((List(gameMapState), ""))
    }
  }

  private def asListResult(function: GameMapState => GameMapState, code: String) = {
    (gameMapState: GameMapState) => {
      List((List(function(gameMapState)), code))
    }
  }

  private def readValueName(function: String): String = function.substring(function.indexOf('(') + 1, function.lastIndexOf(')'))

  private def composeWithValueName(function: GameMapState => GameMapState, valueName: String, code: String)= {
    (gameMapState: GameMapState) => {
      List((if(valueName.matches("\\d+$"))
        composeFunction(function, gameMapState, Integer.parseInt(valueName))
      else
        composeFunction(function, gameMapState, gameMapState.variables(valueName))
        , code))
    }
  }

  private def composeFunction(function: GameMapState => GameMapState, gameMapState: GameMapState, times: Int)  = {
    List((1 to times)
      .map(_ => function)
      .foldLeft((gameMapState: GameMapState) => gameMapState)((a, b) => b.compose(a)))
      .map(a => a apply gameMapState)
  }
}
