package com.mfed.scenario.helpers.functions

import java.util

import com.mfed.model._

/**
  * Created by Maciej Fedorowiat 
  * on 07/03/2017 22:34.
  * mfedorowiat@gmail.com
  */
object ScenarioWithRotates {

  def scenarioWithRotatesFunctions: String = {
    "go(); rotateRight(); go();  rotateRight(); go(); rotateRight(); go(); rotateRight(); go(); rotateRight(5);  go();"
  }

  def scenarioWithRotatesMap: GameMap = {
    GameMap(2, 2, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithRotatesExpectedResult: ExecutionResult = {
    ExecutionResult(List(Move(1, 0, 0), Move(0, 0, 1), Move(0, 1, 0), Move(0, 0, 1), Move(-1, 0, 0), Move(0, 0, 1),
      Move(0, -1, 0), Move(0, 0, 1), Move(1, 0, 0), Move(0, 0, 5), Move(0, 1, 0)), success = true)
  }

}
