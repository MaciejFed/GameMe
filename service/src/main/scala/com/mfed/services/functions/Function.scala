package com.mfed.services.functions

import com.mfed.model.GameMapState

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 13:32.
  * mfedorowiat@gmail.com
  */
trait Function{
  def produce(): GameMapState => GameMapState = ???
  def produce(definition: String): GameMapState => GameMapState = ???
}