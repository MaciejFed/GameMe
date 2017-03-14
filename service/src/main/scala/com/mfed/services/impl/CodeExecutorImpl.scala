package com.mfed.services.impl

import com.mfed.model.{ExecutionResult, GameMap}
import com.mfed.services.{CodeExecutor, FunctionService, RoadService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by Maciej Fedorowiat 
  * on 07/03/2017 20:29.
  * mfedorowiat@gmail.com
  */
@Service
class CodeExecutorImpl extends CodeExecutor{

  @Autowired
  private val roadService: RoadService = null

  @Autowired
  private val functionService: FunctionService = null

  override def executeCodeOnLevel(gameMap: GameMap, code: List[String]): ExecutionResult = {
    val functions = functionService.produceFunctionsFromCode(code)
    roadService.runMovingCodeOnLevel(gameMap, functions)
  }
}
