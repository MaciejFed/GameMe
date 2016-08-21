package com.mfed.api.controllers

import com.mfed.dto._
import com.mfed.api.converters.GameMapSerializatiors._
import com.mfed.services.GameMapService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 01:32.
  * mfedorowiat@gmail.com
  */
@RestController
@RequestMapping(Array("/level"))
class GameMapController {

  @Autowired
  private val gameMapService: GameMapService = null

  @RequestMapping(value = Array("/{levelNumber}"), method = Array(RequestMethod.GET))
  def getGameMap(@PathVariable levelNumber: Int): ResponseEntity[GameMapDTO] = {
    new ResponseEntity[GameMapDTO](gameMapService.getGameMap(levelNumber), HttpStatus.OK)
  }

  @ExceptionHandler(value = Array(classOf[Exception]))
  def reactToException(exception: Exception): ResponseEntity[String] = {
    exception.printStackTrace()
    new ResponseEntity[String](exception.getMessage, HttpStatus.BAD_REQUEST)
  }
}
