package com.mfed

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

/**
  * Created by Maciej Fedorowiat 
  * on 24/08/2016 15:37.
  * mfedorowiat@gmail.com
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringBootTest
@WebAppConfiguration class  GameMeApplicationTests {

  @Autowired
  val applicationContext: ApplicationContext = null

  @Test
  def contextLoads(): Unit = {
    assert(Option(applicationContext).isDefined)
  }
}
