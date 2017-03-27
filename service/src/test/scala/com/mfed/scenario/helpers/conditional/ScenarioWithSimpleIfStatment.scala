package com.mfed.scenario.helpers.conditional

import java.util

import com.mfed.model._

/**
  * Created by Maciej Fedorowiat 
  * on 21/03/2017 22:07.
  * mfedorowiat@gmail.com
  */
object ScenarioWithSimpleIfStatement {
  def scenarioWithSimpleIfStatementFunctions: List[Block] = {
    List(IfBlock((_) => true,
        List(CodeBlock(List("go(1);", "go(3);"))),
        List(CodeBlock(List("go(2);")))))
  }

  def scenarioWithSimpleIfStatementMap: GameMap = {
    GameMap(8, 2, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithSimpleIfStatementExpectedResult: ExecutionResult= {
    ExecutionResult(List(Move(1, 0, 0), Move(3, 0, 0)), success = false)
  }

  def scenarioWithNestedIfStatementFunctions: List[Block] = {
    List(IfBlock((_) => false,
      List(CodeBlock(List("go(1);"))),
      List(IfBlock((_) => true,
        List(CodeBlock(List("go(2);", "go(5);"))),
        List(CodeBlock(List("go(3);")))))))
  }

  def scenarioWithNestedIfStatementMap: GameMap = {
    GameMap(15, 2, new util.LinkedList[(Int, Int)]())
  }

  def scenarioWithNestedIfStatementExpectedResult: ExecutionResult= {
    ExecutionResult(List(Move(2, 0, 0), Move(5, 0, 0)), success = false)
  }
}
