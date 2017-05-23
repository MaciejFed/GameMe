package com.mfed.services.conditions

import com.mfed.model.GameMapState

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 13:42.
  * mfedorowiat@gmail.com
  */
trait Condition {
  def produce(): GameMapState => Boolean = ???
  def produce(condition: String): GameMapState => Boolean = ???
}
