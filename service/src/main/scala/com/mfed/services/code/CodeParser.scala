package com.mfed.services.code

import com.mfed.model.Block

/**
  * Created by Maciej Fedorowiat 
  * on 31/03/2017 09:28.
  * mfedorowiat@gmail.com
  */
trait CodeParser {
  def parseCodeToCodeBlocks(code: String): List[Block]
}
