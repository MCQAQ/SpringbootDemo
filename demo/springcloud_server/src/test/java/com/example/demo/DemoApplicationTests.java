package com.example.demo;

import com.example.demo.Controller.HelloController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoApplicationTests {

	@Autowired
	HelloController helloController;

	@Test
	public void contextLoads() {
		Assert.assertThat(helloController.getHello(),is("Hello World"));
	}

}
