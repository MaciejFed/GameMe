package com.mfed.scenario.helpers.combine

import java.util

import com.mfed.model.{ExecutionResult, GameMap, Move}

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 15:15.
  * mfedorowiat@gmail.com
  */
object ScenarioWithLineByLineRoad {
  def scenarioWithLineByLineRoadFunctions = {
    "while(notFacingWall()){ go(); } rotateRight(); go(); rotateRight(); while(notFacingWall()) { go(); } rotateRight(3); go(); rotateRight(3); while(notFacingWall()) { go(); } rotateRight(); go(); rotateRight();  "
  }

  def scenarioWithLineByLineRoadMap = {
    GameMap(6, 3, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithLineByLineRoadExpectedResult = {
    ExecutionResult(List(
      Move(1, 0, 0), Move(1, 0, 0), Move(1, 0, 0), Move(1, 0, 0), Move(1, 0, 0), Move(0, 0, 1), Move(0, 1, 0), Move(0, 0, 1),
        Move(-1, 0, 0), Move(-1, 0, 0), Move(-1, 0, 0), Move(-1, 0, 0), Move(-1, 0, 0), Move(0, 0, 3), Move(0, 1, 0), Move(0, 0, 3),
      Move(1, 0, 0), Move(1, 0, 0), Move(1, 0, 0), Move(1, 0, 0), Move(1, 0, 0), Move(0, 0, 1), Move(0, 0, 1)), success = true)
  }
}
