package automacao.industrial.maquinario.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class SetorTest {

	@Test
	void testFromCodigoValido() {
		assertEquals(Setor.PECUARIA, Setor.fromCodigo(1));
		assertEquals(Setor.AGRICULTURA, Setor.fromCodigo(2));
		assertEquals(Setor.SILVICULTURA, Setor.fromCodigo(3));
	}
	
	@Test
	void testFromCodigoInvalido() {
		assertNull(Setor.fromCodigo(0));
		assertNull(Setor.fromCodigo(10));
	}
}
