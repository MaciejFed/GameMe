package com.mfed.services

import com.mfed.model.{GameMapState, Move}

/**
  * Created by Maciej Fedorowiat 
  * on 04/02/2017 15:02.
  * mfedorowiat@gmail.com
  */
trait MovesProducer {
  def produceMovesFromGameStates(gameStates: (GameMapState, GameMapState)): Move
}
