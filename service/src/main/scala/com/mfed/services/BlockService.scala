package com.mfed.services

import com.mfed.model.{Block, GameMapState}

/**
  * Created by Maciej Fedorowiat 
  * on 21/03/2017 21:45.
  * mfedorowiat@gmail.com
  */
trait BlockService {
  def produceCodeFromBlocks(blocks: List[Block]): List[GameMapState => GameMapState]
}
