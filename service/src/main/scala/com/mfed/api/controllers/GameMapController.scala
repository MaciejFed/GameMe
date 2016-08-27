package com.mfed.api.controllers

import com.mfed.api.converters.GameMapSerializatiors._
import com.mfed.dto._
import com.mfed.services.{GameMapService, PathService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

import scala.collection.JavaConversions._
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

  @Autowired
  private val pathService: PathService = null

  @RequestMapping(value = Array("/{levelNumber}"), method = Array(RequestMethod.GET))
  def getGameMap(@PathVariable levelNumber: Int): ResponseEntity[GameMapDTO] = {
    new ResponseEntity[GameMapDTO](gameMapService.getGameMap(levelNumber), HttpStatus.OK)
  }

  @RequestMapping(value = Array("/{levelNumber}"), method = Array(RequestMethod.POST))
  def map(@PathVariable levelNumber: Int, @RequestBody pathRequestDTO: PathRequestDTO): ResponseEntity[java.util.List[String]] = {
    val path = pathService.validatePathInLevel(levelNumber, (pathRequestDTO.startPoint.x, pathRequestDTO.startPoint.y),  pathRequestDTO.path.toList)

    new ResponseEntity[java.util.List[String]](path, HttpStatus.OK)
  }

  @ExceptionHandler(value = Array(classOf[Exception]))
  def reactToException(exception: Exception): ResponseEntity[String] = {
    exception.printStackTrace()
    new ResponseEntity[String](exception.getMessage, HttpStatus.BAD_REQUEST)
  }
}
