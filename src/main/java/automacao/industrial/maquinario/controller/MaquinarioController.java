package automacao.industrial.maquinario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maquinarios")
public class MaquinarioController {
	
	@GetMapping("/status")
	public ResponseEntity<String> status(){
		return ResponseEntity.ok("API de Maquinários está ativa!");
	}

}
