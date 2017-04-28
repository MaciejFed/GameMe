package com.mfed.scenario.helpers.conditional

import java.util

import com.mfed.model.{ExecutionResult, GameMap, Move}

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 15:07.
  * mfedorowiat@gmail.com
  */
object ScenarioWithBeforeAndAfterWhileStatement {
  def scenarioWithBeforeAndAfterWhileStatementFunctions: String = {
    "go(4); while(notFacingWall()){ go(1); go(3); } rotateRight(); go();"
  }

  def scenarioWithBeforeAndAfterWhileStatementMap: GameMap = {
    GameMap(12, 2, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithBeforeAndAfterWhileStatementExpectedResult: ExecutionResult= {
    ExecutionResult(List(Move(4, 0, 0), Move(1, 0, 0), Move(3, 0, 0), Move(1, 0, 0), Move(2, 0, 0),Move(0, 0, 1),  Move(0, 1, 0)), success = true)
  }
}
