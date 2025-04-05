package automacao.industrial.maquinario;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import automacao.industrial.maquinario.factory.SetorAgricolaFactory;
import automacao.industrial.maquinario.model.Dispositivo;
import automacao.industrial.maquinario.model.Maquina;
import automacao.industrial.maquinario.model.Sensor;
import automacao.industrial.maquinario.service.RegistroExecucaoService;

public class TipoDispositivoTest {

	@Test
	void testPraMaquina() {
		SetorAgricolaFactory factoryMock = mock(SetorAgricolaFactory.class);
		Maquina maquinaMock = mock(Maquina.class);
		when(factoryMock.maquinaEscolhida()).thenReturn(maquinaMock);
		
		RegistroExecucaoService registroMock = mock(RegistroExecucaoService.class);

		TipoDispositivo tipoDispositivo = new TipoDispositivo(factoryMock, registroMock,
				Dispositivo.MAQUINA, automacao.industrial.maquinario.model.Setor.AGRICULTURA); // setor definido pro teste
		
		tipoDispositivo.praMaquina();

		verify(factoryMock).maquinaEscolhida();
		verify(maquinaMock).operar();
	}

	@Test
	void testPraSensor() {
		SetorAgricolaFactory factoryMock = mock(SetorAgricolaFactory.class);
		Sensor sensorMock = mock(Sensor.class);
		when(factoryMock.sensorEscolhido()).thenReturn(sensorMock);

		RegistroExecucaoService registroMock = mock(RegistroExecucaoService.class);
		
		TipoDispositivo tipoDispositivo = new TipoDispositivo(factoryMock, registroMock,
				Dispositivo.SENSOR, automacao.industrial.maquinario.model.Setor.SILVICULTURA); // setor definido pro teste
		
		
		tipoDispositivo.praSensor();

		verify(factoryMock).sensorEscolhido();
		verify(sensorMock).lerDados();
	}

}
