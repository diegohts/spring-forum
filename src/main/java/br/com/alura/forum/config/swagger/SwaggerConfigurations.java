package br.com.alura.forum.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alura.forum.model.Usuario;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration // Carrega classe de configuração
public class SwaggerConfigurations {
// Endereço para abrir o Swagger: localhost:8080/Swagger-ui.html
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2) // retorna um new docket, especificando o tipo Swagger2
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.alura.forum"))
                .paths(PathSelectors.ant("/**")) // não tem url restrita
                .build()
                .ignoredParameterTypes(Usuario.class) // ignorando todas urls que trabalham na classe Usuario
                .globalOperationParameters( // Aqui configurando o parametro global para enviar token JWT com Swagger
                        Arrays.asList(
                                new ParameterBuilder() // construindo como vai ser o parametro
                                    .name("Authorization") // nome do cabeçalho
                                    .description("Header para Token JWT") // descrição para aparecer na documentação
                                    .modelRef(new ModelRef("string")) // tipo string, um texto
                                    .parameterType("header") // tipo de parametro cabeçalho
                                    .required(false)
                                    .build())); // construir o objeto
    }

}
