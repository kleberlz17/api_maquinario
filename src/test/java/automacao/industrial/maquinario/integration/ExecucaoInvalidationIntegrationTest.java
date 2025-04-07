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
import automacao.industrial.maquinario.repository.RegistroExecucaoRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExecucaoInvalidationIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private RegistroExecucaoRepository repository;
	
	@Test
	void testExecucaoComSetorInvalido() {
		// código de setor inválido (ex:99), dispositivo ainda válido. ex(1)
		ExecucaoRequestDTO requestDTO = new ExecucaoRequestDTO(1, 99);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ExecucaoRequestDTO> request = new HttpEntity<>(requestDTO, headers);
		
		String url = "http://localhost:" + port + "/maquinario/executar";
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		
		// Aqui espera um erro..
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		
		// Confirma que nada foi salvo no banco
		List<RegistroExecucao> registros = repository.findAll();
		assertEquals(0, registros.size());
		
		// Verifica se mensagem de erro é clara..
		assertTrue(response.getBody().toLowerCase().contains("setor"));
		
	}

}
