package com.mfed.services

import com.mfed.model.{ExecutionResult, GameMap}
import com.mfed.services.code.CodeParser
import com.mfed.services.functions.FunctionService
import com.mfed.services.road.RoadRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by Maciej Fedorowiat 
  * on 07/03/2017 20:27.
  * mfedorowiat@gmail.com
  */

@Service
class CodeExecutorService{

  @Autowired
  private val roadRunner: RoadRunner = null

  @Autowired
  private val functionService: FunctionService = null

  @Autowired
  private val codeParser: CodeParser = null

  def executeCodeOnLevel(gameMap: GameMap, code: String): ExecutionResult = {
    val blocks = codeParser.parseCodeToCodeBlocks(code)
    val functions = functionService.produceFunctionsFromCodeBlocks(blocks)

      roadRunner.runMovingCodeOnLevel(gameMap, functions)
  }
}
