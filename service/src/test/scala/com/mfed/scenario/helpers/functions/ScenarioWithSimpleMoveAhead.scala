package com.mfed.scenario.helpers.functions

import java.util

import com.mfed.model.{ExecutionResult, GameMap, Move}

/**
  * Created by Maciej Fedorowiat 
  * on 07/03/2017 22:32.
  * mfedorowiat@gmail.com
  */
object ScenarioWithSimpleMoveAhead {

  def scenarioSimpleMoveAheadFunctions: List[String] = {
    List("go();")
  }

  def scenarioSimpleMoveAheadMap: GameMap = {
    GameMap(2, 1, new util.LinkedList[(Int, Int)]())
  }

  def scenarioSimpleMoveAheadExpectedResult: ExecutionResult = {
    ExecutionResult(List(Move(1, 0, 0)), success = true)
  }

}
