package com.mfed.model
import java.util

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import scala.collection.JavaConversions._
/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 02:22.
  * mfedorowiat@gmail.com
  */
@Document
case class Level(@Id id: String, number: Integer, gameMap: GameMap, introductionText: java.util.List[String], functions: java.util.List[String])


case class GameMap(width: Integer, height: Integer, obstacles: java.util.List[(Int, Int)],
                   diamonds: java.util.List[(Int, Int)] = new util.LinkedList[(Int, Int)]()){

  def endPoint = {
    (width - 1, height - 1)
  }

  def produceInitialState() = GameMapState(this, RobotState(), Map())

  def inValidPlace(point: (Int, Int)): Boolean = {
    (obstacles.toList.forall(obstacle => !obstacle.equals(point))
    && point._1 >= 0 && point._1 < width
    && point._2 >= 0 && point._2 < height)
  }
}

case class Obstacle(x: Integer, y: Integer)



