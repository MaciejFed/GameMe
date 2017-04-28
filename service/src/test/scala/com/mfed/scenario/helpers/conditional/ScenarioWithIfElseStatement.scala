package com.mfed.scenario.helpers.conditional

import java.util

import com.mfed.model.{ExecutionResult, GameMap, Move}

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 15:04.
  * mfedorowiat@gmail.com
  */
object ScenarioWithIfElseStatement {
  def scenarioWithIfElseStatementFunctions: String = {
    "if(false()){ go(1); go(3); } else { go(5); }"
  }

  def scenarioWithIfElseStatementMap: GameMap = {
    GameMap(10, 1, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithIfElseStatementExpectedResult: ExecutionResult= {
    ExecutionResult(List(Move(5, 0, 0)), success = false)
  }
}
