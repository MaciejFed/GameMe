package com.mfed.services.impl

import com.mfed.model.{GameMap, GameMapState}
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

  override def runMovingCodeOnLevel(gameMap: GameMap, moveFunctionList: List[(GameMapState) => GameMapState]) = {
    val gameMapInitialState = gameMap.produceInitialState()

    val gameStates = moveFunctionList
      .scan(moveFunctionList.head)((a, b) => b.compose(a))
      .map(_ apply gameMapInitialState)

    gameStates
      .zip(gameStates.tail)
      .map(s => movesProducer.produceMovesFromGameStates(s._1, s._2))
  }
}

