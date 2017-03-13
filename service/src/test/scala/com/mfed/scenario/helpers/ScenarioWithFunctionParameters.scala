package com.mfed.scenario.helpers

import java.util

import com.mfed.model.{ExecutionResult, GameMap, Move}

/**
  * Created by Maciej Fedorowiat 
  * on 13/03/2017 15:24.
  * mfedorowiat@gmail.com
  */
object ScenarioWithFunctionParameters {
  def scenarioWithFunctionParametersFunctions: List[String] = {
    List("go(3);", "rotateRight(2);", "go(2);" )
  }

  def scenarioWithFunctionParametersMap: GameMap = {
    GameMap(5, 1, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithFunctionParametersExpectedResult: ExecutionResult= {
    ExecutionResult(List(Move(3, 0, 0), Move(0, 0, 2), Move(-2, 0, 0)), success = false)
  }
}
