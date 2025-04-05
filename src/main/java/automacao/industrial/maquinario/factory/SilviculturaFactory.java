package automacao.industrial.maquinario.factory;

import automacao.industrial.maquinario.model.Maquina;
import automacao.industrial.maquinario.model.Sensor;
import automacao.industrial.maquinario.model.Silvicultura;

public class SilviculturaFactory implements SetorAgricolaFactory {

	public Maquina maquinaEscolhida() {
		return new Silvicultura();
	}
	
	public Sensor sensorEscolhido() {
		return new Silvicultura();
	}
}
