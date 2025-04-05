package automacao.industrial.maquinario.factory;

import automacao.industrial.maquinario.model.Agricultura;
import automacao.industrial.maquinario.model.Maquina;
import automacao.industrial.maquinario.model.Sensor;

public class AgriculturaFactory implements SetorAgricolaFactory{
	
	public Maquina maquinaEscolhida() {
		return new Agricultura();
	}

	public Sensor sensorEscolhido() {
		return new Agricultura();
	}
}
