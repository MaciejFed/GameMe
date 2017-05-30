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

  @RequestMapping(value = Array("/{levelName}"), method = Array(RequestMethod.GET))
  def getLevel(@PathVariable levelName: String): ResponseEntity[LevelDTO] = {
    new ResponseEntity[LevelDTO](levelService.getLevel(levelName), HttpStatus.OK)
  }

  @RequestMapping(value = Array("/{levelName}"), method = Array(RequestMethod.POST))
  def runCodeOnMap(@PathVariable levelName: String, @RequestBody codeRequestDTO: CodeRequestDTO): ResponseEntity[RoadResponseDTO] = {
    val gameMap = levelService.getLevel(levelName).gameMap
    val executionResult = codeExecutorService.executeCodeOnLevel(gameMap, codeRequestDTO.code)

    new ResponseEntity[RoadResponseDTO](executionResult, HttpStatus.OK)
  }

  @ExceptionHandler(value = Array(classOf[Exception]))
  def reactToException(exception: Exception): ResponseEntity[String] = {
    exception.printStackTrace()
    new ResponseEntity[String](exception.getMessage, HttpStatus.BAD_REQUEST)
  }
}
