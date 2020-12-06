package com.cola.operaciones.operador;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class ColaOperacionesOperandoApplicationTests {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> ColaOperacionesOperadorApplication.main(new String[0]));
	}

}
