package com.mfed.services.functions

import com.mfed.model.{Block, GameMapState}

/**
  * Created by Maciej Fedorowiat 
  * on 06/02/2017 14:39.
  * mfedorowiat@gmail.com
  */
trait FunctionService {
  def produceFunctionsFromCodeBlocks(code: List[Block]): List[(GameMapState) => List[(List[GameMapState], String)]]
}
