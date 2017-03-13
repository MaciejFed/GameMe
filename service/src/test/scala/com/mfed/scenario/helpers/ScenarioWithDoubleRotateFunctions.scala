package com.mfed.scenario.helpers

import java.util

import com.mfed.model.{ExecutionResult, GameMap, Move}

/**
  * Created by Maciej Fedorowiat 
  * on 07/03/2017 22:35.
  * mfedorowiat@gmail.com
  */
object ScenarioWithDoubleRotateFunctions {

  def scenarioWithDoubleRotateFunctions: List[String] = {
    List("go();", "rotateRight();", "rotateRight();", "go();" )
  }

  def scenarioWithDoubleRotateMap: GameMap = {
    GameMap(3, 2, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithDoubleRotateExpectedResult: ExecutionResult= {
    ExecutionResult(List(Move(1, 0, 0), Move(0, 0, 1), Move(0, 0, 1), Move(-1, 0, 0)), success = false)
  }
}
