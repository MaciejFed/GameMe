package com.mfed.api.converters

import com.mfed.dto._
import com.mfed.model._

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 14:53.
  * mfedorowiat@gmail.com
  */

object GameMapSerializatiors {

  implicit def serializeLevel(level: Level): LevelDTO = {
    new LevelDTO(level.gameMap, level.introductionText, level.functions, level.number)
  }


  implicit def serializeGameMap(gameMap: GameMap): GameMapDTO = {
    val list = gameMap.obstacles.toList.map(o => toObstacleDTO(o)).asJava

    new GameMapDTO(gameMap.height, gameMap.width, list)
  }

  implicit def serializeMoveList(list: List[Move]): java.util.List[MoveDTO] = {
    list.map(m => serializeMove(m))
  }

  implicit def serializeMove(move: Move): MoveDTO = {
    new MoveDTO(move.x, move.y, move.rotation)
  }

  def toObstacleDTO(obstacle: (Int, Int)): ObstacleDTO = {
    new ObstacleDTO(obstacle._1, obstacle._2)
  }

  def toObstacle(obstacleDTO: ObstacleDTO): Obstacle = {
    Obstacle(obstacleDTO.x, obstacleDTO.y)
  }

  implicit def toRoadResponseDto(executionResult: ExecutionResult) = {
    new RoadResponseDTO(executionResult.moves, executionResult.success)
  }
}
