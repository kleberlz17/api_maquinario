package automacao.industrial.maquinario.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import automacao.industrial.maquinario.entity.RegistroExecucao;
import automacao.industrial.maquinario.model.Dispositivo;
import automacao.industrial.maquinario.model.Setor;
import automacao.industrial.maquinario.repository.RegistroExecucaoRepository;

@Service
public class RegistroExecucaoService {

	private final RegistroExecucaoRepository repository;

	public RegistroExecucaoService(RegistroExecucaoRepository repository) {
		this.repository = repository;
	}

	public void registrar(Dispositivo dispositivo, Setor setor, String acaoExecutada) {
		RegistroExecucao registro = new RegistroExecucao();
		registro.setDispositivo(dispositivo);
		registro.setSetor(setor);
		registro.setAcaoExecutada(acaoExecutada);
		registro.setDataHora(LocalDateTime.now());

		repository.save(registro);

	}

}
