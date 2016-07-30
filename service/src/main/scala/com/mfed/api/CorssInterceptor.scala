package com.mfed.api

/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 17:37.
  * mfedorowiat@gmail.com
  */
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CorssInterceptor extends HandlerInterceptorAdapter {
  val CREDENTIALS_NAME = "Access-Control-Allow-Credentials"
  val ORIGIN_NAME = "Access-Control-Allow-Origin"
  val METHODS_NAME = "Access-Control-Allow-Methods"
  val HEADERS_NAME = "Access-Control-Allow-Headers"
  val MAX_AGE_NAME = "Access-Control-Max-Age"

  @Override
  override def preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Object): Boolean = {
    response.setHeader(CREDENTIALS_NAME, "true")
    response.setHeader(ORIGIN_NAME, "*")
    response.setHeader(METHODS_NAME, "GET, OPTIONS, POST, PUT, DELETE")
    response.setHeader(HEADERS_NAME, "Origin, X-Requested-With, Content-Type, Accept")
    response.setHeader(MAX_AGE_NAME, "10")
    true
  }

}