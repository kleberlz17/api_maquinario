package automacao.industrial.maquinario.model;

public class Pecuaria implements Maquina, Sensor {

	public void operar() {
		System.out.println("Operando máquina na Pecuária...");
	}
	
	public void lerDados() {
		System.out.println("Lendo dados da Pecuária...");
	}
}
