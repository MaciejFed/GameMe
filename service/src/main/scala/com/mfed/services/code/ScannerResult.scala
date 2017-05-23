package com.mfed.services.code

/**
  * Created by Maciej Fedorowiat 
  * on 07/04/2017 12:42.
  * mfedorowiat@gmail.com
  */
trait ScannerResult {}
case class FunctionScannerResult(code: String, codeAfter: String) extends ScannerResult
case class IfScannerResult(code: String, codeAfter: String) extends ScannerResult
case class IfElseScannerResult(code: String, codeOnFail: String, codeAfter: String) extends ScannerResult
case class WhileScannerResult(code: String, codeAfter: String) extends ScannerResult
case class DoneScannerResult() extends ScannerResult
