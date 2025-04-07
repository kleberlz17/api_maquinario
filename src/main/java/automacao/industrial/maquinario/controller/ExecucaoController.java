
package automacao.industrial.maquinario.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import automacao.industrial.maquinario.TipoDispositivo;
import automacao.industrial.maquinario.dto.ExecucaoRequestDTO;
import automacao.industrial.maquinario.factory.AgriculturaFactory;
import automacao.industrial.maquinario.factory.PecuariaFactory;
import automacao.industrial.maquinario.factory.SetorAgricolaFactory;
import automacao.industrial.maquinario.factory.SilviculturaFactory;
import automacao.industrial.maquinario.model.Dispositivo;
import automacao.industrial.maquinario.model.Setor;
import automacao.industrial.maquinario.service.RegistroExecucaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/maquinario")
@Tag(name = "Execução de Maquinário", description = "Permite acionar sensores e máquinas para diferentes setores.")
public class ExecucaoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExecucaoController.class);
	
	private final RegistroExecucaoService registroService;
	
	public ExecucaoController(RegistroExecucaoService registroService) {
		this.registroService = registroService;
	}
	
	@PostMapping("/executar")
	@Operation(summary = "Executa um processo industrial com base no dispositivo e setor.")
	public ResponseEntity<String> executar(@RequestBody ExecucaoRequestDTO request){
		logger.info("Requisição recebida: Dispositivo={}, Setor={}",
				request.getCodigoDispositivo(), request.getCodigoSetor());
		
		Dispositivo dispositivo = Dispositivo.fromCodigo(request.getCodigoDispositivo());
		if(dispositivo == null) {
			logger.warn("Dispositivo inválido informado: {}", request.getCodigoDispositivo());
			return ResponseEntity.badRequest().body("Dispositivo inválido.");
		}
		
		Setor setor = Setor.fromCodigo(request.getCodigoSetor());
		if(setor == null) {
			logger.warn("Setor inválido informado: {}", request.getCodigoSetor());
			return ResponseEntity.badRequest().body("Setor inválido.");
		}
		
		SetorAgricolaFactory factory = switch (setor) {
		case PECUARIA -> new PecuariaFactory();
		case AGRICULTURA -> new AgriculturaFactory();
		case SILVICULTURA -> new SilviculturaFactory();
		};
		
		TipoDispositivo tipoDispositivo = new TipoDispositivo(factory, registroService, dispositivo, setor);
		
		switch(dispositivo) {
		case MAQUINA -> tipoDispositivo.praMaquina();
		case SENSOR -> tipoDispositivo.praSensor();
		}
		
		return ResponseEntity.ok("Execução realizada com sucesso!");
		
		
	}
	
}
