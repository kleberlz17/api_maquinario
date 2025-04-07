package automacao.industrial.maquinario.integration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import automacao.industrial.maquinario.controller.ExecucaoController;
import automacao.industrial.maquinario.service.RegistroExecucaoService;

@WebMvcTest(ExecucaoController.class)
public class ExecucaoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private RegistroExecucaoService registroExecucaoService;
    
    // Teste parametrizado com várias combinações de dispositivo e setor. Somente positivas.
    @ParameterizedTest 
    @CsvSource({
        "1, 1, 'Execução realizada com sucesso!'", // MAQUINA + PECUARIA
        "1, 2, 'Execução realizada com sucesso!'", // MAQUINA + AGRICULTURA
        "1, 3, 'Execução realizada com sucesso!'", // MAQUINA + SILVICULTURA
        "2, 1, 'Execução realizada com sucesso!'", // SENSOR + PECUARIA
        "2, 2, 'Execução realizada com sucesso!'", // SENSOR + AGRICULTURA
        "2, 3, 'Execução realizada com sucesso!'"  // SENSOR + SILVICULTURA
    })
    public void testExecucaoMaquinario(int dispositivo, int setor, String expectedMensagem) throws Exception {
        // Montando o corpo da requisição
        String jsonRequest = String.format("{\"codigoDispositivo\": %d, \"codigoSetor\": %d}", dispositivo, setor);
        
        mockMvc.perform(MockMvcRequestBuilders.post("/maquinario/executar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
               .andExpect(MockMvcResultMatchers.status().isOk()) // Verifica se o status é OK
               .andExpect(MockMvcResultMatchers.content().string(expectedMensagem)); // Verifica a mensagem de sucesso
    }
}
