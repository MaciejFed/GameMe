package com.mfed.services.impl

import java.io.File
import java.lang.reflect.Method
import java.net.{URL, URLClassLoader}
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import javax.tools.{JavaCompiler, ToolProvider}

import com.mfed.GameMeApplication
import com.mfed.services.CodeService
import org.springframework.stereotype.Service

@Service
class CodeServiceImpl extends CodeService {
  override def compile(code: String): String = {
    val root: File = prepFile
    val sourceFile: File = new File(root, "Test.java")
    sourceFile.getParentFile.mkdirs
    Files.write(sourceFile.toPath, code.getBytes(StandardCharsets.UTF_8))

    val compiler: JavaCompiler = ToolProvider.getSystemJavaCompiler

    compiler.run(null, null, null, sourceFile.getPath).toString
  }

  override def run(code: String): List[String] = {
    val classLoader: URLClassLoader = URLClassLoader.newInstance(Array[URL](prepFile.toURI.toURL))
    val instance: Any = Class.forName("Test", true, classLoader).newInstance
    val method: Method = instance.getClass.getMethod("run")

    List(String.valueOf(method.invoke(instance)))
  }

  def prepFile: File = {
    val path = classOf[GameMeApplication].getProtectionDomain.getCodeSource.getLocation.getPath.split("out")(0)
    new File(path)
  }
}
