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
  def produceCondition(condition: String): GameMapState => Boolean = {
    condition match {
      case NotFacingWallCondition.name => NotFacingWallCondition.produce()
      case NotInEscapePointCondition.name => NotInEscapePointCondition.produce()
      case TrueFalseCondition.name(c) => TrueFalseCondition.produce(c)
    }
  }
}
