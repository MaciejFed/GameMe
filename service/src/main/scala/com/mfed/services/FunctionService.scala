package com.mfed.services

import com.mfed.model.GameMapState

/**
  * Created by Maciej Fedorowiat 
  * on 06/02/2017 14:39.
  * mfedorowiat@gmail.com
  */
trait FunctionService {
  def produceFunctionsFromCode(code: List[String]): List[(GameMapState => GameMapState)]
}
