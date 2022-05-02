package br.com.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching // Habilitando o uso de cache na aplicacao, para a fins de estudo podemos usar
				// desse jeito, porem em producao eh necessario especificar o provedor de cache
				// como Redis, instalando nas dependencias (pom.xml)
				// A ideia de se eu utilizar o cache, consigo dizer para o Spring guardar o
				// retorno de um método em cache. Na primeira vez que eu chamar aquele método,
				// ele vai executar linha por linha do método e vai devolver o resultado. Porém,
				// nas próximas chamadas, ele já devolve direto o que está em memória. Com isso,
				// fica muito mais rápido o retorno para o cliente que fez a chamada para a API.
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
