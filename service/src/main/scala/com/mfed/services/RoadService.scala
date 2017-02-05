package com.mfed.services

import com.mfed.model.{GameMap, GameMapState, Move}

/**
  * Created by Maciej Fedorowiat 
  * on 03/02/2017 13:11.
  * mfedorowiat@gmail.com
  */
trait RoadService {
  def runMovingCodeOnLevel(gameMap: GameMap, moveFunctionList: List[(GameMapState) => GameMapState]): List[Move]
}
