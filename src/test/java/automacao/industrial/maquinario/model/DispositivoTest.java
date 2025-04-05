package automacao.industrial.maquinario.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class DispositivoTest {
	
	@Test
	void testFromCodigoValido() {
		assertEquals(Dispositivo.MAQUINA, Dispositivo.fromCodigo(1));
		assertEquals(Dispositivo.SENSOR, Dispositivo.fromCodigo(2));
	}
	
	@Test
	void testFromCodigoInvalido() {
		assertNull(Dispositivo.fromCodigo(0)); // Código inválido
		assertNull(Dispositivo.fromCodigo(999)); // Código inexistente
	}
}
