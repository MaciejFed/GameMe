package com.mfed.service

import com.mfed.GameMeApplication
import com.mfed.model.{CodeBlock, IfElseBlock, WhileBlock}
import com.mfed.services.code.CodeParser
import org.junit.Assert._
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
/**
  * Created by Maciej Fedorowiat 
  * on 06/02/2017 15:18.
  * mfedorowiat@gmail.com
  */

@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringBootTest
@WebAppConfiguration class CodeParserTest {

  @Autowired
  private val codeParser: CodeParser = null

  @Test
  def shouldParseSimpleFunctions(): Unit = {
    val code = "go(); go(); goRight();"
    val expectedCodeBlocks = List(CodeBlock(List("go();", "go();", "goRight();")))

    assertEquals(expectedCodeBlocks, codeParser.parseCodeToCodeBlocks(code))
  }

  @Test
  def shouldParseSimpleFunctionsInsideWhileBlock(): Unit = {
    val code = "while(notFacingWall()){ go(); go(); goRight(); } go();"
    val expectedCodeBlocks = List(WhileBlock("notFacingWall()", List(CodeBlock(List("go();", "go();", "goRight();")))), CodeBlock(List("go();")))
    val result = codeParser.parseCodeToCodeBlocks(code)

    assertEquals(expectedCodeBlocks, result)
  }

  @Test
  def shouldParseSimpleFunctionsInsideWhileBlockAndFunctionBeforeAndAfterWhileBLock(): Unit = {
    val code = "go(); while(notFacingWall()){ go(); go(); goRight(); } go();"
    val expectedCodeBlocks = List(CodeBlock(List("go();")), WhileBlock("notFacingWall()", List(CodeBlock(List("go();", "go();", "goRight();")))), CodeBlock(List("go();")))
    val result = codeParser.parseCodeToCodeBlocks(code)

    assertEquals(expectedCodeBlocks, result)
  }

  @Test
  def shouldParseSimpleFunctionsWithIfStatment(): Unit = {
    val code = "go(); if(notFacingWall()){ go(); go(); } else { goRight(); }  go();"
    val expectedCodeBlocks = List(CodeBlock(List("go();")), IfElseBlock("notFacingWall()", List(CodeBlock(List("go();", "go();"))), List(CodeBlock(List("goRight();")))), CodeBlock(List("go();")))
    val result = codeParser.parseCodeToCodeBlocks(code)

    assertEquals(expectedCodeBlocks, result)
  }

//  @Test
//  def shouldCombineIfInWhileStatment(): Unit = {
//    val code = "go(); while(notWin()){ if(notFacingWall()){ go(); go(); } else { goRight( ); } } go();"
//    val expectedCodeBlocks =
//      List(CodeBlock(List("go();")),
//      WhileBlock("notWin()", List(
//        IfElseBlock("notFacingWall()",
//          List(CodeBlock(List("go();", "go();"))),
//          List(CodeBlock(List("goRight();"))),
//            List())),
//          List(CodeBlock(List("go();")))))
//
//    assertEquals(expectedCodeBlocks, codeParser.parseCodeToCodeBlocks(code))
//  }
}
