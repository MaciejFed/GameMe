package com.mfed;

import com.mfed.services.PathService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import scala.collection.mutable.ListBuffer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GameMeApplication.class)
@WebAppConfiguration
public class GameMeApplicationJavaTests {

	@Test
	public void contextLoads() {
	}


}
