package com.mfed.services.impl

import com.mfed.model.{Block, GameMapState}
import com.mfed.services.{BlockService, FunctionService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by Maciej Fedorowiat 
  * on 21/03/2017 21:46.
  * mfedorowiat@gmail.com
  */
@Service
class BlockServiceImpl extends BlockService{

  @Autowired
  private val functionService: FunctionService = null

  override def produceCodeFromBlocks(blocks: List[Block]): List[(GameMapState) => GameMapState] = {
    List()
  }
}
