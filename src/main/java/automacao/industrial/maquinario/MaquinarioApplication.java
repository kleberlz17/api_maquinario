package automacao.industrial.maquinario;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import automacao.industrial.maquinario.factory.AgriculturaFactory;
import automacao.industrial.maquinario.factory.PecuariaFactory;
import automacao.industrial.maquinario.factory.SetorAgricolaFactory;
import automacao.industrial.maquinario.factory.SilviculturaFactory;
import automacao.industrial.maquinario.model.Dispositivo;
import automacao.industrial.maquinario.model.Setor;
import automacao.industrial.maquinario.service.RegistroExecucaoService;

@SpringBootApplication
public class MaquinarioApplication {
	private static final Logger logger = LoggerFactory.getLogger(MaquinarioApplication.class);

	public static void main(String[] args) {
		var context = SpringApplication.run(MaquinarioApplication.class, args);
		logger.info("Sistema iniciado");

		try (Scanner scanner = new Scanner(System.in)) {
			logger.info("Aguardando a entrada do usuário para o tipo de dispositivo: 1)Máquina, 2)Sensor:");
			int codigoDispositivo = scanner.nextInt();

			Dispositivo dispositivoEscolhido = Dispositivo.fromCodigo(codigoDispositivo);
			if (dispositivoEscolhido == null) {
				logger.warn("Dispositivo inválido: {}", codigoDispositivo);
				System.out.println("Opção inválida. Tente novamente.");
				return;
			}

			logger.info(
					"Aguardando a entrada do usuário para o tipo de setor: 1)Pecuária, 2)Agricultura, 3)Silvicultura:");
			int codigoSetor = scanner.nextInt();

			Setor setorEscolhido = Setor.fromCodigo(codigoSetor);
			if (setorEscolhido == null) {
				logger.warn("Setor invalido informado: {}", codigoSetor);
				System.out.println("Setor inválido. Tente novamente.");
				return;
			}

			SetorAgricolaFactory factory = switch (setorEscolhido) {
			case PECUARIA -> new PecuariaFactory();
			case AGRICULTURA -> new AgriculturaFactory();
			case SILVICULTURA -> new SilviculturaFactory();
			default -> throw new IllegalStateException("Erro inesperado");
			};

			RegistroExecucaoService registroService = context.getBean(RegistroExecucaoService.class);
			
			TipoDispositivo  tipoDispositivo = new TipoDispositivo(factory, registroService,
					dispositivoEscolhido, setorEscolhido);
			
			switch (dispositivoEscolhido) {
			case MAQUINA -> tipoDispositivo.praMaquina();
			case SENSOR -> tipoDispositivo.praSensor();
			default -> logger.error("Dispositivo inválido no switch");
			}

		} catch (Exception e) {
			logger.error("Erro no sistema: ", e);
		}

		logger.info("Sistema finalizado");

	}

}
