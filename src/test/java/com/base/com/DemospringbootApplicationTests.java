package com.base.com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.base.DemospringbootApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemospringbootApplication.class)
@WebAppConfiguration
public class DemospringbootApplicationTests {

	@Test
	public void contextLoads() {
	}

}
