package com.mfed.services.impl

import java.io.File
import java.net.{URL, URLClassLoader}
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import javax.tools.{ToolProvider, JavaCompiler}

import com.mfed.GameMeApplication
import com.mfed.services.CodeService
import org.springframework.stereotype.Service

@Service
class CodeServiceImpl extends CodeService{
  override def compile(code: String): String = {
    val root: File = prepFile
    val sourceFile: File = new File(root, "Test.java")
    sourceFile.getParentFile.mkdirs
    Files.write(sourceFile.toPath, code.getBytes(StandardCharsets.UTF_8))

    val compiler: JavaCompiler = ToolProvider.getSystemJavaCompiler

    compiler.run(null, null, null, sourceFile.getPath).toString
  }

  override def run(code: String): List[String] = {
    val root = prepFile
    val classLoader: URLClassLoader = URLClassLoader.newInstance(Array[URL](root.toURI.toURL))
    val cls: Class[_] = Class.forName("Test", true, classLoader)
    val instance: Any = cls.newInstance
    List(instance.toString)
  }

  def prepFile: File = {
    val path = classOf[GameMeApplication].getProtectionDomain.getCodeSource.getLocation.getPath.split("out")(0)
    new File(path)
  }
}
