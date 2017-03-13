package com.mfed.services.impl

import com.mfed.model.{GameMapState, Move, RobotState}
import com.mfed.services.MovesProducer
import org.springframework.stereotype.Component

/**
  * Created by Maciej Fedorowiat 
  * on 04/02/2017 15:04.
  * mfedorowiat@gmail.com
  */
@Component
class GameMovesProducerImpl extends MovesProducer{
  override def produceMovesFromGameStates(gameStates: (GameMapState, GameMapState)) = {
    val gameStateDifference = detectGameStateDifference(gameStates)

     gameStateDifference match {
      case RobotStateDifference(_, _) => new RobotStateDifferenceMoveProducer().produceMove(gameStateDifference.asInstanceOf[RobotStateDifference])
      case NoDifference(_, _) => new NoDifferenceMoveProducer().produceMove(gameStateDifference.asInstanceOf[NoDifference])
    }
  }

  def detectGameStateDifference(gameMapState: (GameMapState, GameMapState)): GameStateDifference ={
    if(!gameMapState._1.robotState.equals(gameMapState._2.robotState))
       RobotStateDifference(gameMapState._1.robotState, gameMapState._2.robotState)
    else
      NoDifference(Unit, Unit)
  }
}

trait GameStateDifference{
  def before: Any
  def after: Any
}

case class RobotStateDifference(override val before: RobotState, override val after: RobotState) extends GameStateDifference
case class MapStateDifference(override val before: RobotState, override val after: RobotState) extends GameStateDifference
case class NoDifference(override val before: Any, override val after: Any) extends GameStateDifference

trait DifferenceToMoveProducer[A <: GameStateDifference]{
  def produceMove(gameStateDifference: A): Move
}

class RobotStateDifferenceMoveProducer extends DifferenceToMoveProducer[RobotStateDifference]{
  override def produceMove(gameStateDifference: RobotStateDifference): Move = {
    Move(gameStateDifference.after.point._1 - gameStateDifference.before.point._1,
      gameStateDifference.after.point._2 - gameStateDifference.before.point._2,
      gameStateDifference.after.rotation - gameStateDifference.before.rotation)
  }
}

class NoDifferenceMoveProducer extends DifferenceToMoveProducer[NoDifference]{
  override def produceMove(gameStateDifference: NoDifference): Move = Move.emptyMove
}
