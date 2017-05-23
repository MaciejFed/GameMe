package com.mfed.scenario.helpers.variables

import java.util

import com.mfed.model._

/**
  * Created by Maciej Fedorowiat 
  * on 13/03/2017 16:03.
  * mfedorowiat@gmail.com
  */
object ScenarioWithIntVariable {
  def scenarioWithIntVariableFunctions: String = {
    "go();def x = 3; go(x);"
  }

  def scenarioWithIntVariableMap: GameMap = {
    GameMap(5, 1, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithIntVariableExpectedResult: ExecutionResult = {
    ExecutionResult(List(Move(1, 0, 0), Move(3, 0, 0)), success = true)
  }

}
