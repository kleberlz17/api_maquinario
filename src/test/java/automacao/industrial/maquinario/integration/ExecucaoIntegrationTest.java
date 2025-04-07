package automacao.industrial.maquinario.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import automacao.industrial.maquinario.dto.ExecucaoRequestDTO;
import automacao.industrial.maquinario.entity.RegistroExecucao;
import automacao.industrial.maquinario.model.Dispositivo;
import automacao.industrial.maquinario.model.Setor;
import automacao.industrial.maquinario.repository.RegistroExecucaoRepository;

// TESTA A APLICAÇÃO TODA, COM SERVIDOR SUBINDO, ENDPOINT SENDO CHAMADO E BANCO INTERAGINDO;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExecucaoIntegrationTest {

	@LocalServerPort
	private int port; // captura a porta onde o app está rodando

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private RegistroExecucaoRepository repository; // acesso ao banco para validar os dados salvos

	@Test
	void testExecucaoMaquinarioFluxoCompleto() {
		// Dados de entrada simulando chamada real da API.
		ExecucaoRequestDTO requestDTO = new ExecucaoRequestDTO(Dispositivo.MAQUINA.ordinal(),
				Setor.AGRICULTURA.ordinal());

		// configura headers e conteudo do body como json
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ExecucaoRequestDTO> request = new HttpEntity<>(requestDTO, headers);

		// monta a URL que será chamada.
		String url = "http://localhost:" + port + "/maquinario/executar";

		// Faz chamada POST para o endpoint da API
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		// Validações da resposta HTTP
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains("Executando"));

		// Confirma que foi salvo no banco
		List<RegistroExecucao> registros = repository.findAll();
		assertEquals(1, registros.size());

		assertEquals("MAQUINA", registros.get(0).getDispositivo().name());
		assertEquals("AGRICULTURA", registros.get(0).getSetor().name());

	}

}
