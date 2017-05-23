package com.mfed.services.code

import com.mfed.model._
import org.springframework.stereotype.Component

/**
  * Created by Maciej Fedorowiat
  * on 31/03/2017 09:36.
  * mfedorowiat@gmail.com
  */
@Component
class CodeParserImpl extends CodeParser{

  private val whileRegex = "(while\\(.*\\(\\)\\)\\s*\\{.+\\})".r
  private val ifRegex = "(if\\(.*\\(\\)\\)\\s*\\{.*\\})".r
  private val ifElseRegex = "(if\\(.*\\(\\)\\)\\s*\\{.+\\}\\s*else\\s*\\{.+\\}\\s*)".r
  private val conditionRegex = "\\(\\w*\\(\\)\\)".r

  override def parseCodeToCodeBlocks(codeString: String): List[Block] = {
    scanCode(codeString) match {
      case FunctionScannerResult(code, codeAfter) => readFunctions(code) ::: parseCodeToCodeBlocks(codeAfter)
      case IfScannerResult(code, codeAfter) => scanForIfBlock(code) ::: parseCodeToCodeBlocks(codeAfter)
      case IfElseScannerResult(code, codeOnFail, codeAfter) => scanForIfElseBlock(code + codeOnFail) ::: parseCodeToCodeBlocks(codeAfter)
      case WhileScannerResult(code, codeAfter) => scanForWhileBlock(code) ::: parseCodeToCodeBlocks(codeAfter)
      case DoneScannerResult() => List()
    }
  }

  private def scanForWhileBlock(code: String): List[WhileBlock] = {
    val conditionString = conditionRegex.findFirstMatchIn(code).get.matched
    List(WhileBlock(
      conditionString.substring(1, conditionString.length - 1).replaceAll("\\s",""),
      parseCodeToCodeBlocks(code.substring(code.indexOf("{") + 1, code.lastIndexOf("}")))
    ))
  }

  private def scanForIfBlock(code: String): List[IfBlock] = {
    val conditionString = conditionRegex.findFirstMatchIn(code).get.matched
    List(IfBlock(
      conditionString.substring(1, conditionString.length - 1).replaceAll("\\s",""),
      parseCodeToCodeBlocks(code.substring(code.indexOf("{") + 1,  code.lastIndexOf("}")))
    ))
  }

  private def scanForIfElseBlock(code: String): List[IfElseBlock] = {
    val ifElseCode = ifElseRegex.findFirstMatchIn(code)

    ifElseCode match {
      case Some(ifElseMatch) => {
        val ifElseCodeString = ifElseMatch.matched
        val conditionString = conditionRegex.findFirstMatchIn(ifElseCodeString).get.matched
        List(IfElseBlock(
          conditionString.substring(1, conditionString.length - 1).replaceAll("\\s",""),
          parseCodeToCodeBlocks(ifElseCodeString.substring(ifElseCodeString.indexOf("{") + 1, ifElseCodeString.indexOf("else"))),
          parseCodeToCodeBlocks(ifElseCodeString.replaceAll("\\s","").substring(ifElseCodeString.indexOf("else") + 1))
        ))
      }
      case None => List()
    }
  }

  private def readFunctions(code: String): List[CodeBlock] ={
    List(CodeBlock(code.split("(?<=;)")
                       .map(f => if (!f.contains("def")) f.replaceAll("\\s","") else f.substring(f.indexOf("def")))
                       .filter(f => f.length > 2).toList))
  }

  private def scanCode(code: String): ScannerResult = {
    val actionIndexMap = List("if", "else", "while", ";").map(f => f -> code.indexOf(f)).filter(a => a._2 != -1).sortBy(_._2)
    val nextAction = if(code.isEmpty || actionIndexMap.isEmpty) "none" else actionIndexMap.head._1
    val flowMarkWordMap = actionIndexMap.filter(a => !a._1.equals(";"))

    nextAction match {
      case "if" => produceIfScannerResult(code, flowMarkWordMap)
      case "while" => produceWhileScannerResult(code, flowMarkWordMap)
      case ";" => produceFunctionScannerResult(code, flowMarkWordMap)
      case "none" => DoneScannerResult()
    }
}

  private def produceIfScannerResult(code: String, actionIndexMap: Seq[(String, Int)]): ScannerResult = {
    if(actionIndexMap.size < 2 || !actionIndexMap(1)._1.equals("else"))
      IfScannerResult(code.substring(0, findEndOfBlockIndex(code)), code.substring(findEndOfBlockIndex(code)))
    else
      IfElseScannerResult(code.substring(0, actionIndexMap(1)._2),
        code.substring(actionIndexMap(1)._2).substring(0, findEndOfBlockIndex(code.substring(actionIndexMap(1)._2))),
        code.substring(findEndOfBlockIndex(code.substring(actionIndexMap(1)._2)) + actionIndexMap(1)._2))
  }

  private def produceWhileScannerResult(code: String, actionIndexMap: Seq[(String, Int)]) = {
    WhileScannerResult(code.substring(0, findEndOfBlockIndex(code)), code.substring(findEndOfBlockIndex(code), code.length))
  }

  private def produceFunctionScannerResult(code: String, actionIndexMap: Seq[(String, Int)]) = {
    if(actionIndexMap.isEmpty || actionIndexMap.head._2 == -1)
      FunctionScannerResult(code, "")
    else
      FunctionScannerResult(code.substring(0, actionIndexMap.head._2), code.substring(actionIndexMap.head._2))
  }

  private def findEndOfBlockIndex(code: String): Int = {
    val cutIndex = code.indexOf("{") + 1
    code.substring(cutIndex).scanLeft(1) {
      case(a, '{') => a + 1
      case(a, '}') => a - 1
      case(a, _)   => a
    }.indexWhere(_ == 0) + cutIndex
  }

}


