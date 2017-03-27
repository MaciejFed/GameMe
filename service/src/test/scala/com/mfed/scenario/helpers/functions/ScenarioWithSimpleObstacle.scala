package com.mfed.scenario.helpers.functions

import com.mfed.model._

import scala.collection.JavaConverters._
/**
  * Created by Maciej Fedorowiat 
  * on 07/03/2017 22:32.
  * mfedorowiat@gmail.com
  */
object ScenarioWithSimpleObstacle {

  def scenarioSimpleObstacleFunctions: List[Block] = {
    List(new CodeBlock(List("go();", "go();", "go();")))
  }

  def scenarioSimpleObstacleMap: GameMap = {
    GameMap(5, 1, List((2, 0)).asJava)
  }

  def scenarioSimpleObstacleExpectedResult: ExecutionResult = {
    ExecutionResult(List(Move(1, 0, 0)), success = false)
  }

}
