package automacao.industrial.maquinario.entity;

import java.time.LocalDateTime;

import automacao.industrial.maquinario.model.Dispositivo;
import automacao.industrial.maquinario.model.Setor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Getters setters e não precisa declarar as variaveis.
@NoArgsConstructor // criar construtor sem argumentos(vazio)
@AllArgsConstructor // cria um construtor com todos os atributos da classe.
public class RegistroExecucao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Dispositivo dispositivo;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Setor setor;
	
	
	private String acaoExecutada;
	private LocalDateTime dataHora;

}
