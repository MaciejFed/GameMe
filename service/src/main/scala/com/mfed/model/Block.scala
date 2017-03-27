package com.mfed.model

/**
  * Created by Maciej Fedorowiat 
  * on 20/03/2017 16:01.
  * mfedorowiat@gmail.com
  */
trait Block {}

case class CodeBlock(functions: List[String]) extends Block
case class IfBlock(val condition: GameMapState => Boolean, onSuccessBlocks: List[Block],  onFailureBlocks: List[Block]) extends Block



