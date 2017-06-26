package com.mfed.services.functions

import com.mfed.model._
import com.mfed.services.conditions.ConditionProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * Created by Maciej Fedorowiat 
  * on 06/02/2017 14:40.
  * mfedorowiat@gmail.com
  */
@Component
class FunctionServiceImpl extends FunctionService{

  @Autowired
  private val functionProducer: FunctionProducer = null

  @Autowired
  private val conditionProducer: ConditionProducer = null

  type StatesWithCode = (List[GameMapState], String)

  override def produceFunctionsFromCodeBlocks(blocks: List[Block]): List[(GameMapState) => List[StatesWithCode]] = {
      blocks.flatMap { _ match {
        case CodeBlock(f) => functionProducer.produceFunctionsFromCode(f)
        case WhileBlock(condition, onSuccessBlocks) => handleWhileCase(condition, onSuccessBlocks)
        case IfBlock(condition, onSuccessBlocks) => handleIfCase(condition, onSuccessBlocks)
        case IfElseBlock(condition, onSuccessBlocks, onFailureBlocks) => handleIfElseCase(condition, onSuccessBlocks, onFailureBlocks)
      }
    }
  }
  private def handleWhileCase(condition: String, onSuccessBlocks: List[Block]): List[(GameMapState) => List[StatesWithCode]] = {
    List((gameMapState: GameMapState) => {
      var returnStates = List[StatesWithCode]()
      var currentState = gameMapState
      while (conditionProducer.produceCondition(condition)(currentState) && returnStates.size < 100){
        returnStates = returnStates ::: produceStatesFromBlocks(currentState, condition, onSuccessBlocks)
        currentState = returnStates.last._1.last
      }
      returnStates
    })
  }

  private def handleIfCase(condition: String, onSuccessBlocks: List[Block]): List[(GameMapState) => List[StatesWithCode]] = {
    List((gameMapState: GameMapState) => produceStatesFromBlocks(gameMapState, condition, onSuccessBlocks))
  }

  private def handleIfElseCase(condition: String, onSuccessBlocks: List[Block], onFaliureBlocks: List[Block]): List[(GameMapState) => List[StatesWithCode]] = {
    List((gameMapState: GameMapState) => {
      val codeBlocks = if(conditionProducer.produceCondition(condition)(gameMapState)) onSuccessBlocks else onFaliureBlocks
      produceStatesFromBlocks(gameMapState, "true()", codeBlocks)
    })
  }

  private def produceStatesFromBlocks(gameMapState: GameMapState, condition: String, blocks: List[Block]): List[StatesWithCode] = {
    val identify: GameMapState => List[(List[GameMapState], String)] = g => List((List(g), ""))
    var returnStates = List[StatesWithCode]()
    var currentState = gameMapState

    if (conditionProducer.produceCondition(condition)(currentState)){
      val func: List[List[StatesWithCode]] = produceFunctionsFromCodeBlocks(blocks)
        .scan(identify)((a, b) => b.compose((gameMapState: GameMapState) => if(a(gameMapState).last._1.nonEmpty) a(gameMapState).last._1.last else gameMapState))
        .map(s => s(currentState))

      returnStates = returnStates ::: func.flatten
      currentState = func.last.last._1.last
    }
    returnStates
  }
}


