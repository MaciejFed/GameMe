package com.mfed.scenario.helpers.conditional

import java.util

import com.mfed.model.{ExecutionResult, GameMap, Move}

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 15:06.
  * mfedorowiat@gmail.com
  */
object ScenarioWithNestedWhileStatement {
  def scenarioWithNestedWhileStatementFunctions: String = {
    "while(notFacingWall()){ while(notFacingWall()){ go(2); } rotateRight(); }"
  }

  def scenarioWithNestedWhileStatementMap: GameMap = {
    GameMap(10, 1, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithNestedWhileStatementExpectedResult: ExecutionResult= {
    ExecutionResult(List(Move(2, 0, 0), Move(2, 0, 0), Move(2, 0, 0), Move(2, 0, 0), Move(1, 0, 0), Move(0, 0, 1)), success = true)
  }
}
