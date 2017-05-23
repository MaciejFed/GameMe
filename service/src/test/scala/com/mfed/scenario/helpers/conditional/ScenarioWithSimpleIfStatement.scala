package com.mfed.scenario.helpers.conditional

import java.util

import com.mfed.model._

/**
  * Created by Maciej Fedorowiat 
  * on 21/03/2017 22:07.
  * mfedorowiat@gmail.com
  */
object ScenarioWithSimpleIfStatement {
  def scenarioWithSimpleIfStatementFunctions: String = {
    "if(true()){ go(1); go(3); go(5); }"
  }

  def scenarioWithSimpleIfStatementMap: GameMap = {
    GameMap(10, 1, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithSimpleIfStatementExpectedResult: ExecutionResult= {
    ExecutionResult(List(Move(1, 0, 0), Move(3, 0, 0), Move(5, 0, 0)), success = true)
  }
}
