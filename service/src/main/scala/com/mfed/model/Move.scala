package com.mfed.model

/**
  * Created by Maciej Fedorowiat 
  * on 03/02/2017 13:08.
  * mfedorowiat@gmail.com
  */
case class Move(x: Int, y: Int, rotation: Int, diamonds: List[(Int, Int)] = List[(Int, Int)]()) {}

object Move{
  def emptyMove = Move(0, 0, 0, List[(Int, Int)]())
}
