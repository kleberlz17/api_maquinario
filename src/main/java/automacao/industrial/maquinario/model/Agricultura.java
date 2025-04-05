package automacao.industrial.maquinario.model;

public class Agricultura implements Maquina, Sensor{

	public void operar() {
		System.out.println("Operando máquina na Agricultura...");
	}
	
	public void lerDados() {
		System.out.println("Lendo dados da Agricultura...");
	}
}
