package com.mfed.api.controllers

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
  def getGameMap(@PathVariable levelNumber: Int): ResponseEntity[java.util.Map[Object, Object]] = {
    new ResponseEntity[java.util.Map[Object, Object]](gameMapService.getGameMap(levelNumber), HttpStatus.OK)
  }

  @RequestMapping(method = Array(RequestMethod.POST))
  def saveGameMap(@PathVariable levelNumber: Int, @RequestBody gameMap: java.util.Map[Object, Object]): ResponseEntity[java.util.Map[Object, Object]] = {
    new ResponseEntity[java.util.Map[Object, Object]](gameMapService.saveGameMap(asGameObject(gameMap)), HttpStatus.OK)
  }

  @ExceptionHandler(value = Array(classOf[Exception]))
  def reactToException(exception: Exception): ResponseEntity[String] = {
    exception.printStackTrace()
    new ResponseEntity[String](exception.getMessage, HttpStatus.BAD_REQUEST)
  }
}
