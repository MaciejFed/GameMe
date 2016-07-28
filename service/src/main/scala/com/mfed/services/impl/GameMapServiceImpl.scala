package com.mfed.services.impl

import com.mfed.api.exceptions._
import com.mfed.model.GameMap
import com.mfed.repositories.{GameMapRepository, GameMapRepositoryCustom}
import com.mfed.services.GameMapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.collection.JavaConversions._

/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 01:48.
  * mfedorowiat@gmail.com
  */

@Service
class GameMapServiceImpl extends GameMapService{

  @Autowired
  private val gameMapRepository: GameMapRepository = null

  @Autowired
  private val gameMapRepositoryCustom: GameMapRepositoryCustom = null

  override def saveGameMap(gameMap: GameMap): GameMap = {
    if(Option(gameMapRepositoryCustom.findByLevelNumber(gameMap.levelNumber)).isEmpty)
      gameMapRepository.save(gameMap)
    else
      throw GameMapAlreadyExistsException(s"Level ${gameMap.levelNumber} already exists")
  }

  override def getGameMap(levelNumber: Int): GameMap = {
    if(Option(gameMapRepositoryCustom.findByLevelNumber(levelNumber)).isDefined)
      gameMapRepositoryCustom.findByLevelNumber(levelNumber)
    else
      throw GameMapNotFoundException(s"Level $levelNumber was not found")
  }

  override def getAllGameMaps: List[GameMap] =  gameMapRepository.findAll().toList

  override def getGameMapCount: Long = gameMapRepository.count()

}
