package com.mfed.services.moves

import com.mfed.model.{GameMapState, Move, RobotState}
import org.springframework.stereotype.Component

/**
  * Created by Maciej Fedorowiat 
  * on 04/02/2017 15:04.
  * mfedorowiat@gmail.com
  */
@Component
class MovesProducer{
  def produceMovesFromGameStates(gameStates: (GameMapState, GameMapState)) = {
    val gameStateDifference = detectGameStateDifference(gameStates)

     gameStateDifference match {
      case RobotStateDifference(_, _) => new RobotStateDifferenceMoveProducer().produceMove(gameStateDifference.asInstanceOf[RobotStateDifference])
      case NoDifference(_, _) => new NoDifferenceMoveProducer().produceMove(gameStateDifference.asInstanceOf[NoDifference])
    }
  }

  private def detectGameStateDifference(gameMapState: (GameMapState, GameMapState)): GameStateDifference ={
    if(!gameMapState._1.robotState.equals(gameMapState._2.robotState))
       RobotStateDifference(gameMapState._1.robotState, gameMapState._2.robotState)
    else
      NoDifference(Unit, Unit)
  }
}

private trait GameStateDifference{
  def before: Any
  def after: Any
}

private case class RobotStateDifference(override val before: RobotState, override val after: RobotState) extends GameStateDifference
private case class MapStateDifference(override val before: RobotState, override val after: RobotState) extends GameStateDifference
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
