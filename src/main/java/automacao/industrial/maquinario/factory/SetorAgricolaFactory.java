package automacao.industrial.maquinario.factory;

import automacao.industrial.maquinario.model.Maquina;
import automacao.industrial.maquinario.model.Sensor;

public interface SetorAgricolaFactory {

	Maquina maquinaEscolhida();
	Sensor sensorEscolhido();
}
