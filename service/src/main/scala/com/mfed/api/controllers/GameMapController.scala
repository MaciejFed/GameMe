package com.mfed.api.controllers

import com.mfed.api.converters.GameMapSerializatiors._
import com.mfed.dto._
import com.mfed.services.{FunctionService, GameMapService, RoadService}
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
  private val roadService: RoadService = null

  @Autowired
  private val functionService: FunctionService = null

  @RequestMapping(value = Array("/{levelNumber}"), method = Array(RequestMethod.GET))
  def getGameMap(@PathVariable levelNumber: Int): ResponseEntity[GameMapDTO] = {
    new ResponseEntity[GameMapDTO](gameMapService.getGameMap(levelNumber), HttpStatus.OK)
  }

  @RequestMapping(value = Array("/{levelNumber}"), method = Array(RequestMethod.POST))
  def runCodeOnMap(@PathVariable levelNumber: Int, @RequestBody codeRequestDTO: CodeRequestDTO): ResponseEntity[java.util.List[MoveDTO]] = {
    val gameMap = gameMapService.getGameMap(levelNumber)
    val functions = functionService.produceFunctionsFromCode(codeRequestDTO.code.toList)
    val road = roadService.runMovingCodeOnLevel(gameMap, functions)

    new ResponseEntity[java.util.List[MoveDTO]](road, HttpStatus.OK)
  }

  @ExceptionHandler(value = Array(classOf[Exception]))
  def reactToException(exception: Exception): ResponseEntity[String] = {
    exception.printStackTrace()
    new ResponseEntity[String](exception.getMessage, HttpStatus.BAD_REQUEST)
  }
}
