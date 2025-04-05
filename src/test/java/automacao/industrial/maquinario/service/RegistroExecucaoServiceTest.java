package automacao.industrial.maquinario.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import automacao.industrial.maquinario.entity.RegistroExecucao;
import automacao.industrial.maquinario.model.Dispositivo;
import automacao.industrial.maquinario.model.Setor;
import automacao.industrial.maquinario.repository.RegistroExecucaoRepository;

@DataJpaTest
public class RegistroExecucaoServiceTest {
	
	@Autowired
	private RegistroExecucaoRepository repository;
	
	@Test
	void testRegistroEhSalvoCorretamente() {
		RegistroExecucaoService service = new RegistroExecucaoService(repository);
		
		service.registrar(Dispositivo.MAQUINA, Setor.AGRICULTURA, "Teste de operação");
		
		List<RegistroExecucao> registros = repository.findAll();
		assertEquals(1, registros.size());
		
		RegistroExecucao registro = registros.get(0);
		assertEquals(Dispositivo.MAQUINA, registro.getDispositivo());
		assertEquals(Setor.AGRICULTURA, registro.getSetor());
		assertEquals("Teste de operação", registro.getAcaoExecutada());
		assertNotNull(registro.getDataHora());
	}

}
