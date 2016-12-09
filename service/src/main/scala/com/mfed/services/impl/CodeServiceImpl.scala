package com.mfed.services.impl

import com.mfed.services.CodeService
import org.springframework.stereotype.Service

@Service
class CodeServiceImpl extends CodeService{
  override def compile(code: String): String = ???

  override def run(code: String): List[String] = ???
}
