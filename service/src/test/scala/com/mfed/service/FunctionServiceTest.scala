package com.mfed.service

import java.util

import com.mfed.GameMeApplication
import com.mfed.model.GameMap
import com.mfed.services.FunctionService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

/**
  * Created by Maciej Fedorowiat 
  * on 06/02/2017 15:18.
  * mfedorowiat@gmail.com
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringApplicationConfiguration(classes = Array(classOf[GameMeApplication]))
@WebAppConfiguration class FunctionServiceTest {

  @Autowired
  val functionService: FunctionService = null

  @Test
  def shouldConvertStringCodeToFunctionsAndExecuteThemChangingStateInCorrectWay(): Unit = {
    val codeFunctions = List("go();", "go(9);", "wrongFunction();", "rotateRight();")
    val functions = functionService.produceFunctionsFromCode(codeFunctions)
    val gameMap = GameMap(7, 7, new util.LinkedList[(Int, Int)]())
    val gameMapState = gameMap.produceInitialState()

    assert(functions.size.equals(4))
    assert(functions(0)(gameMapState).robotState.point._1.equals(1))
    assert(functions(1)(gameMapState).robotState.point._1.equals(6))
    assert(functions(2)(gameMapState).robotState.point._1.equals(0))
    assert(functions(3)(gameMapState).robotState.rotation.equals(2))
  }
}
