package com.mfed.scenario.helpers.conditional

import java.util

import com.mfed.model.{ExecutionResult, GameMap, Move}

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 15:06.
  * mfedorowiat@gmail.com
  */
object ScenarioWithSimpleWhileStatement {
  def scenarioWithSimpleWhileStatementFunctions: String = {
    "while(notFacingWall()){ go(1); go(3); }"
  }

  def scenarioWithSimpleWhileStatementMap: GameMap = {
    GameMap(8, 1, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithSimpleWhileStatementExpectedResult: ExecutionResult= {
    ExecutionResult(List(Move(1, 0, 0), Move(3, 0, 0), Move(1, 0, 0), Move(2, 0, 0)), success = true)
  }
}
