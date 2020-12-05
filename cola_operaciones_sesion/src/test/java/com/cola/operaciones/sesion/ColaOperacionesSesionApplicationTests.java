package com.cola.operaciones.sesion;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class ColaOperacionesSesionApplicationTests {

	@Test()
	void contextLoads() {
		assertDoesNotThrow(() -> ColaOperacionesSesionApplication.main(new String[0]));
	}

}
