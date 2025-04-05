package automacao.industrial.maquinario.model;

public enum Dispositivo {
	MAQUINA(1), SENSOR(2);

	private final int codigo;

	Dispositivo(int codigo) {
		this.codigo = codigo;
	}

	public int getCodigo() {
		return codigo;
	}

	public static Dispositivo fromCodigo(int codigo) {
		for (Dispositivo dispositivo : values()) {
			if (dispositivo.codigo == codigo) {
				return dispositivo;
			}
		}

		return null;

	}
}
