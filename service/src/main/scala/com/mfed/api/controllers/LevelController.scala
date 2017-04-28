package com.mfed.api.controllers

import com.mfed.api.converters.GameMapSerializatiors._
import com.mfed.dto._
import com.mfed.services.{CodeExecutorService, LevelService}
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
class LevelController {

  @Autowired
  private val codeExecutorService: CodeExecutorService = null

  @Autowired
  private val levelService: LevelService = null

  @RequestMapping(value = Array("/{levelNumber}"), method = Array(RequestMethod.GET))
  def getLevel(@PathVariable levelNumber: Int): ResponseEntity[LevelDTO] = {
    new ResponseEntity[LevelDTO](levelService.getLevel(levelNumber), HttpStatus.OK)
  }

  @RequestMapping(value = Array("/{levelNumber}"), method = Array(RequestMethod.POST))
  def runCodeOnMap(@PathVariable levelNumber: Int, @RequestBody code: String): ResponseEntity[RoadResponseDTO] = {
    val gameMap = levelService.getLevel(levelNumber).gameMap
    val executionResult = codeExecutorService.executeCodeOnLevel(gameMap, code)

    new ResponseEntity[RoadResponseDTO](executionResult, HttpStatus.OK)
  }

  @ExceptionHandler(value = Array(classOf[Exception]))
  def reactToException(exception: Exception): ResponseEntity[String] = {
    exception.printStackTrace()
    new ResponseEntity[String](exception.getMessage, HttpStatus.BAD_REQUEST)
  }
}
