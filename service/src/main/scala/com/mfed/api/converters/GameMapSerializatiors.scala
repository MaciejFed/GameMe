package com.mfed.api.converters

import com.mfed.model.{GameMap, Obstacle}

import scala.collection.JavaConversions._
/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 14:53.
  * mfedorowiat@gmail.com
  */
object GameMapSerializatiors {

  implicit def serialize(gameMap: GameMap): java.util.Map[Object, Object] = {

    val serializedMap: Map[Object, Object] =
      Map(
        "id" -> gameMap.id,
        "levelNumber" -> gameMap.levelNumber,
        "width" -> gameMap.width,
        "height" -> gameMap.height,
        "obstacles" -> bufferAsJavaList(gameMap.obstacles.map(serialize))
      )
    serializedMap
  }


  implicit def serialize(obstacle: Obstacle): java.util.Map[Object, Object] = {

    val serializedMap: Map[Object, Object] =
      Map(
        "x" -> obstacle.x,
        "y" -> obstacle.y
      )

    serializedMap
  }

  def asGameObject(gameMap: java.util.Map[Object, Object]): GameMap = {
    GameMap(
      gameMap.get("id").asInstanceOf[String],
      gameMap.get("levelNumber").asInstanceOf[Integer],
      gameMap.get("width").asInstanceOf[Integer],
      gameMap.get("height").asInstanceOf[Integer],
      gameMap.get("obstacles").asInstanceOf[java.util.List[java.util.Map[Object, Object]]].map(s => asObstacle(s))
    )
  }

  def asObstacle(obstacle: java.util.Map[Object, Object]): Obstacle = {
    Obstacle(
      obstacle.get("x").asInstanceOf[Integer],
      obstacle.get("y").asInstanceOf[Integer])
  }
}
