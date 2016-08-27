package com.mfed.api.exceptions

/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 17:02.
  * mfedorowiat@gmail.com
  */

case class GameMapNotFoundException(message: String) extends RuntimeException(message){}
case class GameMapAlreadyExistsException(message: String) extends RuntimeException(message){}
case class WrongDirectionException(message: String) extends RuntimeException(message){}
