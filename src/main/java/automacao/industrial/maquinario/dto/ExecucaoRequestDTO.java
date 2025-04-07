package automacao.industrial.maquinario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data //Getters and Setters
@AllArgsConstructor
public class ExecucaoRequestDTO {

	@Schema(description = "Código do dispositivo a ser executado. exemplo = 1")
	private int codigoDispositivo;
	
	@Schema(description = "Código do setor a ser executado. exemplo = 2")
	private int codigoSetor;
	
}
