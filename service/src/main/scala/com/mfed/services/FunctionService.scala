package com.mfed.services

import com.mfed.model.{Block, GameMapState}

/**
  * Created by Maciej Fedorowiat 
  * on 06/02/2017 14:39.
  * mfedorowiat@gmail.com
  */
trait FunctionService {
  def produceFunctionsFromCodeBlocks(code: List[Block]): List[(GameMapState) => List[GameMapState]]
}
