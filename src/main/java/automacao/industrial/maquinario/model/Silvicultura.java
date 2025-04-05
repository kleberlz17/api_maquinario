package automacao.industrial.maquinario.model;

public class Silvicultura implements Maquina, Sensor{
	
	public void operar() {
		System.out.println("Operando máquina na Silvicultura...");
	}
	
	public void lerDados() {
		System.out.println("Lendo dados da Silvicultura...");
	}

}
