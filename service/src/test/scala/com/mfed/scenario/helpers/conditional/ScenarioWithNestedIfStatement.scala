package com.mfed.scenario.helpers.conditional

import java.util

import com.mfed.model.{ExecutionResult, GameMap, Move}

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 15:05.
  * mfedorowiat@gmail.com
  */
object ScenarioWithNestedIfStatement {
  def scenarioWithNestedIfStatementFunctions: String = {
    "if(true()){ go(1); if(true()) {go(2);}  go(4); if(false()){go(3);}} "
  }

  def scenarioWithNestedIfStatementMap: GameMap = {
    GameMap(15, 2, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithNestedIfStatementExpectedResult: ExecutionResult= {
    ExecutionResult(List(Move(1, 0, 0), Move(2, 0, 0), Move(4, 0, 0)), success = false)
  }
}
