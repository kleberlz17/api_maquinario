package automacao.industrial.maquinario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import automacao.industrial.maquinario.entity.RegistroExecucao;

public interface RegistroExecucaoRepository extends JpaRepository<RegistroExecucao, Long> {
	// Posso adicionar aqui métodos personalizados no futuro.

}
