package com.mfed.scenario

import com.mfed.GameMeApplication
import com.mfed.scenario.helpers.combine.ScenarioWithLineByLineRoad._
import com.mfed.scenario.helpers.conditional.ScenarioWithBeforeAndAfterWhileStatement._
import com.mfed.scenario.helpers.conditional.ScenarioWithIfElseStatement._
import com.mfed.scenario.helpers.conditional.ScenarioWithNestedIfStatement._
import com.mfed.scenario.helpers.conditional.ScenarioWithNestedWhileStatement._
import com.mfed.scenario.helpers.conditional.ScenarioWithSimpleIfStatement._
import com.mfed.scenario.helpers.conditional.ScenarioWithSimpleWhileStatement._
import com.mfed.scenario.helpers.functions.ScenarioWithDoubleRotateFunctions._
import com.mfed.scenario.helpers.functions.ScenarioWithFunctionParameters._
import com.mfed.scenario.helpers.functions.ScenarioWithRotates._
import com.mfed.scenario.helpers.functions.ScenarioWithSimpleMoveAhead._
import com.mfed.scenario.helpers.functions.ScenarioWithSimpleObstacle._
import com.mfed.scenario.helpers.functions.ScenarioWithSingleRotate._
import com.mfed.scenario.helpers.variables.ScenarioWithIntVariable._
import com.mfed.services.CodeExecutorService
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
  val codeExecutor: CodeExecutorService = null


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
  def rotatesTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithRotatesMap, scenarioWithRotatesFunctions)

    assertEquals(scenarioWithRotatesExpectedResult, result)
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

  @Test
  def ifStatementTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithSimpleIfStatementMap, scenarioWithSimpleIfStatementFunctions)

    assertEquals(scenarioWithSimpleIfStatementExpectedResult, result)
  }

  @Test
  def ifElseStatementTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithIfElseStatementMap, scenarioWithIfElseStatementFunctions)

    assertEquals(scenarioWithIfElseStatementExpectedResult, result)
  }

  @Test
  def ifElseWithWhileStatementTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithIfElseStatementMap, scenarioWithIfElseStatementFunctions)

    assertEquals(scenarioWithIfElseStatementExpectedResult, result)
  }

  @Test
  def isNestedTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithNestedIfStatementMap, scenarioWithNestedIfStatementFunctions)

    assertEquals(scenarioWithNestedIfStatementExpectedResult, result)
  }

  @Test
  def simpleWhileTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithSimpleWhileStatementMap, scenarioWithSimpleWhileStatementFunctions)

    assertEquals(scenarioWithSimpleWhileStatementExpectedResult, result)
  }

  @Test
  def nestedWhileTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithNestedWhileStatementMap, scenarioWithNestedWhileStatementFunctions)

    assertEquals(scenarioWithNestedWhileStatementExpectedResult, result)
  }

  @Test
  def functionsBeforeAndAfterWhileTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithBeforeAndAfterWhileStatementMap, scenarioWithBeforeAndAfterWhileStatementFunctions)

    assertEquals(scenarioWithBeforeAndAfterWhileStatementExpectedResult, result)
  }

  @Test
  def longTest(): Unit = {
    val result = codeExecutor.executeCodeOnLevel(scenarioWithLineByLineRoadMap, scenarioWithLineByLineRoadFunctions)

    assertEquals(scenarioWithLineByLineRoadExpectedResult, result)
  }

}
