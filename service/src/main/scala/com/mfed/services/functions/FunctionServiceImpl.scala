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

  override def produceFunctionsFromCodeBlocks(blocks: List[Block]): List[(GameMapState) => List[GameMapState]] = {
      blocks.flatMap { _ match {
        case CodeBlock(f) => functionProducer.produceFunctionsFromCode(f)
        case WhileBlock(condition, onSuccessBlocks) => handleWhileCase(condition, onSuccessBlocks)
        case IfBlock(condition, onSuccessBlocks) => handleIfCase(condition, onSuccessBlocks)
        case IfElseBlock(condition, onSuccessBlocks, onFailureBlocks) => handleIfElseCase(condition, onSuccessBlocks, onFailureBlocks)
      }
    }
  }

  private def handleWhileCase(condition: String, onSuccessBlocks: List[Block]): List[(GameMapState) => List[GameMapState]] = {
    List((gameMapState: GameMapState) => {
      var returnStates = List[GameMapState]()
      var currentState = gameMapState
      while (conditionProducer.produceCondition(condition)(currentState) && returnStates.size < 100){
        returnStates = returnStates ::: produceStatesFromBlocks(currentState, condition, onSuccessBlocks)
        currentState = returnStates.last
      }
      returnStates
    })
  }

  private def handleIfCase(condition: String, onSuccessBlocks: List[Block]): List[(GameMapState) => List[GameMapState]] = {
    List((gameMapState: GameMapState) => produceStatesFromBlocks(gameMapState, condition, onSuccessBlocks))
  }

  private def handleIfElseCase(condition: String, onSuccessBlocks: List[Block], onFaliureBlocks: List[Block]): List[(GameMapState) => List[GameMapState]] = {
    List((gameMapState: GameMapState) => {
      val codeBlocks = if(conditionProducer.produceCondition(condition)(gameMapState)) onSuccessBlocks else onFaliureBlocks
      produceStatesFromBlocks(gameMapState, "true()", codeBlocks)
    })
  }

  private def produceStatesFromBlocks(gameMapState: GameMapState, condition: String, blocks: List[Block]) = {
    val identify: GameMapState => List[GameMapState]= g => List(g)
    var returnStates = List[GameMapState]()
    var currentState = gameMapState

    if (conditionProducer.produceCondition(condition)(currentState)){
      val func = produceFunctionsFromCodeBlocks(blocks)
        .scan(identify)((a, b) => b.compose((gameMapState: GameMapState) => if(a(gameMapState).nonEmpty) a(gameMapState).last else gameMapState))
        .flatMap(s => s(currentState))

      returnStates = returnStates ::: func
      currentState = func.last
    }
    returnStates
  }
}


