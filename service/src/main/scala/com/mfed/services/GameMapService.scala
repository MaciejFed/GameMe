package com.mfed.services

import com.mfed.model.GameMap

/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 01:47.
  * mfedorowiat@gmail.com
  */

trait GameMapService {
  def saveGameMap(gameMap: GameMap): GameMap
  def getAllGameMaps: List[GameMap]
  def getGameMapCount: Long
  def getGameMap(levelNumber: Int): GameMap
}
