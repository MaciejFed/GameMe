package com.mfed.services.conditions

import com.mfed.model.GameMapState
import org.springframework.stereotype.Component

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 14:18.
  * mfedorowiat@gmail.com
  */
@Component
class ConditionProducer {
  val falseOperator = "(?=.*?not|!)^.*$"

  def produceCondition(condition: String): GameMapState => Boolean = {
    val logicOperator = !condition.matches(falseOperator)

    val conditionProduct = condition match {
      case FacingWallCondition.name(c) => FacingWallCondition.produce()
      case InEscapePointCondition.name(c) => InEscapePointCondition.produce()
      case TrueFalseCondition.name(c) => TrueFalseCondition.produce(c)
      case _ => throw new RuntimeException("Unknown exception: " + condition)
    }

    (gameMapState: GameMapState) => logicOperator == conditionProduct(gameMapState)
  }
}
