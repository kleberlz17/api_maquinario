package automacao.industrial.maquinario.model;



public enum Setor {
	PECUARIA(1), AGRICULTURA(2), SILVICULTURA(3);
	
	private final int codigo;
	
	Setor(int codigo){
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public static Setor fromCodigo(int codigo) {
		for(Setor setor : values()) {
			if(setor.codigo == codigo) {
				return setor;
			}
		}
		
		return null;
	}
}
