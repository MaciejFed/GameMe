package com.mfed.api.controllers

import com.mfed.services.{CodeService, PathService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

import scala.collection.JavaConversions._

@RestController
@RequestMapping(Array("/code"))
class CodeController {

    @Autowired
    private val codeService: CodeService = null

    @Autowired
    private val pathService: PathService = null

    @RequestMapping(value = Array("/compile"), method = Array(RequestMethod.POST))
    def compile(@RequestBody code: String): ResponseEntity[String] = {
      val result = codeService.compile(code)

      new ResponseEntity[String](result, HttpStatus.OK)
    }

    @RequestMapping(value = Array("/{levelNumber}/run"), method = Array(RequestMethod.POST))
    def run(@PathVariable levelNumber: Int, @RequestBody code: String): ResponseEntity[java.util.List[String]] = {
      codeService.compile(code)
      val result = codeService.run(code)
      val resPath = pathService.validatePathInLevel(levelNumber, (0,0), result)

      new ResponseEntity[java.util.List[String]](resPath, HttpStatus.OK)
    }

    @ExceptionHandler(value = Array(classOf[Exception]))
    def reactToException(exception: Exception): ResponseEntity[String] = {
      exception.printStackTrace()
      new ResponseEntity[String](exception.getMessage, HttpStatus.BAD_REQUEST)
    }
}