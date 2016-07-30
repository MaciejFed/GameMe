package com.mfed.configuration

import com.mfed.api.CorssInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.{InterceptorRegistry, WebMvcConfigurerAdapter}

/**
  * Created by Maciej Fedorowiat 
  * on 28/07/2016 17:42.
  * mfedorowiat@gmail.com
  */
@Configuration
class CrossConfiguration extends WebMvcConfigurerAdapter{
  override def addInterceptors(registry: InterceptorRegistry) {
    registry.addInterceptor(new CorssInterceptor())
  }
}
