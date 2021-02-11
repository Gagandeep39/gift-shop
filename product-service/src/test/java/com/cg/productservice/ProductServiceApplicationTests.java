package com.cg.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = { "test" })
class ProductServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
