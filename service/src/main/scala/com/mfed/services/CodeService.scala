package com.mfed.services


trait CodeService {
    def compile(code : String): String
    def run(code: String): List[String]
}
