package com.mfed.services.functions

import com.mfed.model.GameMapState

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 13:34.
  * mfedorowiat@gmail.com
  */
object SaveVariableFunction extends Function{
  override def produce(definition: String) = {
    (gameMapState: GameMapState) => {
      val name = definition.substring(3, definition.indexOf('=')).replaceAll("\\s", "")
      val value = Integer.parseInt(definition.substring(definition.indexOf('=') + 1, definition.length - 1).replaceAll("\\s", ""))

      gameMapState.copy(variables = gameMapState.variables + (name -> value))
    }
  }
}

