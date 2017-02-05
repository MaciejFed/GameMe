package com.mfed.service

import com.mfed.GameMeApplication
import com.mfed.model.{GameMapState, RobotState}
import com.mfed.services.RoadService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

/**
  * Created by Maciej Fedorowiat 
  * on 03/02/2017 13:15.
  * mfedorowiat@gmail.com
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringApplicationConfiguration(classes = Array(classOf[GameMeApplication]))
@WebAppConfiguration class RoadServiceTest {

  @Autowired
  val roadService: RoadService = null

  @Test
  def shouldReturnEmptyListWhenNoRunnableCodeIsGiven(): Unit = {
    val firstGameStateChange = (gameMapState: GameMapState) => {
      gameMapState.copy(robotState = RobotState((1, 1)))
    }
    val secondGameStateChange = (gameMapState: GameMapState) => {
      gameMapState.copy(robotState = RobotState((2, 2)))
    }
    val thirdGameStateChange = (gameMapState: GameMapState) => {
      gameMapState.copy(robotState = RobotState((5, 5)))
    }

    val gameMoves = roadService.runMovingCodeOnLevel(null, List(firstGameStateChange, secondGameStateChange, thirdGameStateChange))

    assert(gameMoves(0).x.equals(1) && gameMoves(0).y.equals(1))
    assert(gameMoves(1).x.equals(3) && gameMoves(1).y.equals(3))
    assert(gameMoves.size.equals(2))
  }

  @Test
  def shouldReturn2EmptyMoves(): Unit = {
    val firstGameStateChange = (gameMapState: GameMapState) => {
      gameMapState.copy(robotState = RobotState((1, 1)))
    }
    val secondGameStateChange = (gameMapState: GameMapState) => {
      gameMapState.copy(robotState = RobotState((1, 1)))
    }
    val thirdGameStateChange = (gameMapState: GameMapState) => {
      gameMapState.copy(robotState = RobotState((1, 1)))
    }

    val gameMoves = roadService.runMovingCodeOnLevel(null, List(firstGameStateChange, secondGameStateChange, thirdGameStateChange))

    assert(gameMoves(0).x.equals(0) && gameMoves(0).y.equals(0))
    assert(gameMoves(1).x.equals(0) && gameMoves(1).y.equals(0))
    assert(gameMoves.size.equals(2))
  }

}
