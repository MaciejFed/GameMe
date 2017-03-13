package com.mfed.model
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 02:22.
  * mfedorowiat@gmail.com
  */
@Document
case class Level(@Id id: String, number: Integer, gameMap: GameMap, introductionText: java.util.List[String], functions: java.util.List[String])


case class GameMap(width: Integer, height: Integer, obstacles: java.util.List[(Int, Int)]){

  def endPoint = {
    (width - 1, height - 1)
  }

  def produceInitialState() = GameMapState(this, RobotState(), Map())
}

case class Obstacle(x: Integer, y: Integer)



