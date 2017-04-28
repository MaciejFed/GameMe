package com.mfed.services.conditions

import scala.util.matching.Regex

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 14:21.
  * mfedorowiat@gmail.com
  */
object TrueFalseCondition extends Condition{
  val name: Regex = "(false\\(\\)|true\\(\\))".r

  override def produce(condition: String) = {
    if(condition.contains("true")) _ => true else  _ => false
  }
}
