package com.mfed.services

import com.mfed.model.{ExecutionResult, GameMap}

/**
  * Created by Maciej Fedorowiat 
  * on 07/03/2017 20:27.
  * mfedorowiat@gmail.com
  */
trait CodeExecutor {
  def executeCodeOnLevel(gameMap: GameMap, code: List[String]): ExecutionResult
}