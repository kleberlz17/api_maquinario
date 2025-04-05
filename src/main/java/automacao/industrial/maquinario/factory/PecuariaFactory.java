package automacao.industrial.maquinario.factory;

import automacao.industrial.maquinario.model.Maquina;
import automacao.industrial.maquinario.model.Pecuaria;
import automacao.industrial.maquinario.model.Sensor;

public class PecuariaFactory implements SetorAgricolaFactory{

	public Maquina maquinaEscolhida() {
		return new Pecuaria();
	}
	
	public Sensor sensorEscolhido() {
		return new Pecuaria();
		
	}
}
