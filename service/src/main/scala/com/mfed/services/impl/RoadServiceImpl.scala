package com.mfed.services.impl

import com.mfed.model.{ExecutionResult, GameMap, GameMapState, Move}
import com.mfed.services.{MovesProducer, RoadService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by Maciej Fedorowiat 
  * on 03/02/2017 13:13.
  * mfedorowiat@gmail.com
  */

@Service
class RoadServiceImpl extends RoadService{

  @Autowired
  val movesProducer: MovesProducer = null

  override def  runMovingCodeOnLevel(gameMap: GameMap, moveFunctionList: List[(GameMapState) => List[GameMapState]]) = {
    val gameMapInitialState = gameMap.produceInitialState()
    val identify: GameMapState => List[GameMapState]= g => List(g)

    val gameStates = moveFunctionList
      .scan(identify)((a, b) => {
        (gameMapState: GameMapState) => {
          a(gameMapState).flatMap(r => b(r))
        }
      })
      .flatMap(f => f(gameMapInitialState))

    val moves = gameStates
        .zip(gameStates.tail)
        .map(s => movesProducer.produceMovesFromGameStates(s._1, s._2))
        .filter(m => !m.equals(Move.emptyMove))

    ExecutionResult(moves, gameStates.last.robotState.point.equals(gameMap.endPoint))
  }
}

