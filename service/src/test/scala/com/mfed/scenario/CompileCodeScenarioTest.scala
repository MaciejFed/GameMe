package com.mfed.scenario

import com.mfed.GameMeApplication
import com.mfed.scenario.helpers.functions.ScenarioWithDoubleRotateFunctions._
import com.mfed.scenario.helpers.functions.ScenarioWithFunctionParameters._
import com.mfed.scenario.helpers.functions.ScenarioWithSimpleMoveAhead._
import com.mfed.scenario.helpers.functions.ScenarioWithSimpleObstacle._
import com.mfed.scenario.helpers.functions.ScenarioWithSingleRotate._
import com.mfed.scenario.helpers.variables.ScenarioWithIntVariable._
import com.mfed.services.CodeExecutor
import org.junit.Assert._
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

/**
  * Created by Maciej Fedorowiat 
  * on 07/03/2017 20:22.
  * mfedorowiat@gmail.com
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringApplicationConfiguration(classes = Array(classOf[GameMeApplication]))
@WebAppConfiguration
class CompileCodeScenarioTest {

  @Autowired
  val codeExecutor: CodeExecutor = null

  @Test
  def oneStepAheadTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioSimpleMoveAheadMap, scenarioSimpleMoveAheadFunctions)

    assertEquals(scenarioSimpleMoveAheadExpectedResult, result)
  }

  @Test
  def oneRotateTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithSingleRotateMap, scenarioWithSingleRotateFunctions)

    assertEquals(scenarioWithSingleRotateExpectedResult, result)
  }

  @Test
  def doubleRotateTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithDoubleRotateMap, scenarioWithDoubleRotateFunctions)

    assertEquals(scenarioWithDoubleRotateExpectedResult, result)
  }

  @Test
  def simpleObstacleTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioSimpleObstacleMap, scenarioSimpleObstacleFunctions)

    assertEquals(scenarioSimpleObstacleExpectedResult, result)
  }

  @Test
  def functionParametersTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithFunctionParametersMap, scenarioWithFunctionParametersFunctions)

    assertEquals(scenarioWithFunctionParametersExpectedResult, result)
  }

  @Test
  def variableTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithIntVariableMap, scenarioWithIntVariableFunctions)

    assertEquals(scenarioWithIntVariableExpectedResult, result)
  }

}
