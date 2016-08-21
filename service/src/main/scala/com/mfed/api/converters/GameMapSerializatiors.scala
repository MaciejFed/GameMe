package com.mfed.api.converters

import com.mfed.dto.{GameMapDTO, ObstacleDTO}
import com.mfed.model.{GameMap, Obstacle}

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 14:53.
  * mfedorowiat@gmail.com
  */
object GameMapSerializatiors {


  implicit def serializeGameMap(gameMap: GameMap): GameMapDTO = {
    val list = gameMap.obstacles.toList.map(o => toObstacleDTO(o)).asJava

    new GameMapDTO(gameMap.id, gameMap.levelNumber, gameMap.width, gameMap.height, list)
  }

  def toObstacleDTO(obstacle: Obstacle): ObstacleDTO = {
    new ObstacleDTO(obstacle.x, obstacle.y)
  }

  def toObstacle(obstacleDTO: ObstacleDTO): Obstacle = {
    Obstacle(obstacleDTO.x, obstacleDTO.y)
  }
}
