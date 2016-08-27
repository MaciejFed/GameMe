package com.mfed

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

/**
  * Created by Maciej Fedorowiat 
  * on 24/08/2016 15:37.
  * mfedorowiat@gmail.com
  */
@RunWith(classOf[SpringJUnit4ClassRunner])
@SpringApplicationConfiguration(classes = Array(classOf[GameMeApplication]))
@WebAppConfiguration class GameMeApplicationTests {

  @Test
  def contextLoads(): Unit = {
  }
}
