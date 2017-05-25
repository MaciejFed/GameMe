package com.mfed.scenario.helpers.diamonds

import java.util

import com.mfed.model._

/**
  * Created by Maciej Fedorowiat 
  * on 07/03/2017 22:34.
  * mfedorowiat@gmail.com
  */
object ScenarioWithDiamonds {

  def scenarioWithDiamondsFunctions: String = {
    "go(); go(); go();"
  }

  def scenarioWithDiamondsMap: GameMap = {
    GameMap(4, 1, new util.LinkedList[(Int, Int)](), util.Arrays.asList((1, 0)))
  }

  def scenarioWithDiamondsExpectedResult: ExecutionResult = {
    ExecutionResult(List(Move(1, 0, 0), Move(0, 0, 0, List((1, 0))), Move(1, 0, 0), Move(1, 0, 0)), success = true)
  }

}
