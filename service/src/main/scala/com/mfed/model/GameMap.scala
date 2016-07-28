package com.mfed.model

/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 02:22.
  * mfedorowiat@gmail.com
  */

@Document
case class GameMap(@Id id: String, levelNumber: Integer, width: Integer, height: Integer, obstacles: java.util.List[Obstacle]) {}

case class Obstacle(x: Integer, y: Integer)



