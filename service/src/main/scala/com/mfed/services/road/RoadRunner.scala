package com.mfed.services.road

import com.mfed.model.{ExecutionResult, GameMap, GameMapState, Move}
import com.mfed.services.moves.MovesProducer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by Maciej Fedorowiat 
  * on 03/02/2017 13:13.
  * mfedorowiat@gmail.com
  */

@Service
class RoadRunner{

  @Autowired
  val movesProducer: MovesProducer = null

  def runMovingCodeOnLevel(gameMap: GameMap, moveFunctionList: List[(GameMapState) => List[(List[GameMapState], String)]]) = {
    val gameMapInitialState = gameMap.produceInitialState()
    val identify: GameMapState => List[(List[GameMapState], String)] = g => List((List(g), ""))

    val gameStates = moveFunctionList
        .scan(identify)((a, b) => b.compose((gameMapState: GameMapState) => a(gameMapState).last._1.last))
        .flatMap(f => f(gameMapInitialState))

    val moves = gameStates
        .flatMap(s => s._1)
        .zip(gameStates.tail.flatMap(s => s._1))
        .flatMap(s => movesProducer.produceMovesFromGameStates(s._1, s._2))
        .filter(m => !m.equals(Move.emptyMove))

    ExecutionResult(moves, gameStates.last._1.last.robotState.point.equals(gameMap.endPoint))
  }
}

