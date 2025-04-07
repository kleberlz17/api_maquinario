package automacao.industrial.maquinario.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	// ABAIXO, CONFIGURA AS INFORMAÇÕES GERAIS DA DOCUMENTAÇÃO DA API NO SWAGGER.

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(
				new Info().title("API de Maquinários - Sistema de Execução de Maquinários").version("1.0").description(
						"API responsável por gerenciar a execução de máquinas industriais, com design Abstract Factory."));
	}

	// ABAIXO, 'group' define o nome que aparecerá na lista do Swagger.
	// 'pathsToMatch' deve corresponder ao caminho real dos seus endpoints.
	// ex: se os controllers usam @RequestMapping("/maquinario"), entao use
	// "/maquinario/***".
	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("maquinario").pathsToMatch("/maquinario/**").build();

	}

}
