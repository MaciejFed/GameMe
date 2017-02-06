package com.mfed.model
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 02:22.
  * mfedorowiat@gmail.com
  */

@Document
case class GameMap(@Id id: String, levelNumber: Integer, width: Integer, height: Integer, obstacles: java.util.List[(Int, Int)],
                   introductionText: java.util.List[String], functions: java.util.List[String]){
  def produceInitialState() = GameMapState(this, RobotState())
}

case class Obstacle(x: Integer, y: Integer)



