package com.mfed.scenario.helpers.functions

import java.util

import com.mfed.model._

/**
  * Created by Maciej Fedorowiat 
  * on 07/03/2017 22:34.
  * mfedorowiat@gmail.com
  */
object ScenarioWithSingleRotate {

  def scenarioWithSingleRotateFunctions: List[Block] = {
    List(new CodeBlock(List("go();", "rotateRight();", "go();" )))
  }

  def scenarioWithSingleRotateMap: GameMap = {
    GameMap(2, 2, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithSingleRotateExpectedResult: ExecutionResult = {
    ExecutionResult(List(Move(1, 0, 0), Move(0, 0, 1), Move(0, 1, 0)), success = true)
  }

}
