package automacao.industrial.maquinario.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Gera getters and setters, toString() e hashCode()
@AllArgsConstructor // Cria construtor com todos os campos.
public class ErroResponse {
	
	private String erro;
	private LocalDateTime timestamp;
	private int status;

}
