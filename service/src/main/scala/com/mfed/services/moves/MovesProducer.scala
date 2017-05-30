package com.mfed.services.moves

import com.mfed.model.{GameMap, GameMapState, Move, RobotState}
import org.springframework.stereotype.Component
import scala.collection.JavaConversions._
/**
  * Created by Maciej Fedorowiat
  * on 04/02/2017 15:04.
  * mfedorowiat@gmail.com
  */
@Component
class MovesProducer{
  def produceMovesFromGameStates(gameStates: (GameMapState, GameMapState)): List[Move] = {
    val gameStateDifferences = detectGameStateDifferences(gameStates)

    gameStateDifferences.map {
      case RobotStateDifference(before, after) => new RobotStateDifferenceMoveProducer().produceMove(RobotStateDifference(before, after))
      case DiamondDifference(before, after) => new DiamondMoveProducer().produceMove(DiamondDifference(before, after))
      case NoDifference(before, after) => new NoDifferenceMoveProducer().produceMove(NoDifference(before, after))
    }
  }

  private def detectGameStateDifferences(gameMapState: (GameMapState, GameMapState)): List[GameStateDifference] = {
    val robotStateDifference = if(!gameMapState._1.robotState.equals(gameMapState._2.robotState))
       RobotStateDifference(gameMapState._1.robotState, gameMapState._2.robotState)
    else
      NoDifference(Unit, Unit)

    val diamondDifference = if(!gameMapState._1.gameMap.diamonds.equals(gameMapState._2.gameMap.diamonds))
      DiamondDifference(gameMapState._1.gameMap, gameMapState._2.gameMap)
    else
      NoDifference(Unit, Unit)

    List(robotStateDifference, diamondDifference)
  }
}

private trait GameStateDifference{
  def before: Any
  def after: Any
}

private case class RobotStateDifference(override val before: RobotState, override val after: RobotState) extends GameStateDifference
private case class MapStateDifference(override val before: RobotState, override val after: RobotState) extends GameStateDifference
private case class DiamondDifference(override val before: GameMap, override val after: GameMap) extends GameStateDifference
private case class NoDifference(override val before: Any, override val after: Any) extends GameStateDifference

private trait DifferenceToMoveProducer[A <: GameStateDifference]{
  def produceMove(gameStateDifference: A): Move
}

private class RobotStateDifferenceMoveProducer extends DifferenceToMoveProducer[RobotStateDifference]{
  override def produceMove(gameStateDifference: RobotStateDifference): Move = {
    Move(gameStateDifference.after.point._1 - gameStateDifference.before.point._1,
      gameStateDifference.after.point._2 - gameStateDifference.before.point._2,
      gameStateDifference.after.rotation  - gameStateDifference.before.rotation )
  }
}

private class NoDifferenceMoveProducer extends DifferenceToMoveProducer[NoDifference]{
  override def produceMove(gameStateDifference: NoDifference): Move = Move.emptyMove
}

private class DiamondMoveProducer extends DifferenceToMoveProducer[DiamondDifference]{
  override def produceMove(gameStateDifference: DiamondDifference): Move = {
    Move(0, 0, 0, gameStateDifference.before.diamonds.toList.filter(d => !gameStateDifference.after.diamonds.contains(d)))
  }
}
