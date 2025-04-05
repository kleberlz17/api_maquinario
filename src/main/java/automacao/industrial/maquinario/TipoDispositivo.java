package automacao.industrial.maquinario;

import automacao.industrial.maquinario.factory.SetorAgricolaFactory;
import automacao.industrial.maquinario.model.Dispositivo;
import automacao.industrial.maquinario.model.Maquina;
import automacao.industrial.maquinario.model.Sensor;
import automacao.industrial.maquinario.model.Setor;
import automacao.industrial.maquinario.service.RegistroExecucaoService;

public class TipoDispositivo {

	private final SetorAgricolaFactory factory;
	private final RegistroExecucaoService registroService;
	private Dispositivo dispositivo;
	private Setor setor;

	public TipoDispositivo(SetorAgricolaFactory factory, RegistroExecucaoService registroService,
			Dispositivo dispositivo, Setor setor) {
		this.factory = factory;
		this.registroService = registroService;
		this.dispositivo = dispositivo;
		this.setor = setor;
		
	}


	public void praMaquina() {
		Maquina maquina = factory.maquinaEscolhida();
		maquina.operar();
		registroService.registrar(dispositivo, setor, "Máquina operando");
	}

	public void praSensor() {
		Sensor sensor = factory.sensorEscolhido();
		sensor.lerDados();
		registroService.registrar(dispositivo, setor, "Sensor lendo dados");
	}

}
