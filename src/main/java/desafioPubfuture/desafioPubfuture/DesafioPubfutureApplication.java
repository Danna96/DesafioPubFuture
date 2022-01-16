package desafioPubfuture.desafioPubfuture;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Desafio PubFuture!", description = "Gerenciador de finanças pessoais."))
@SpringBootApplication
public class DesafioPubfutureApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioPubfutureApplication.class, args);
	}

}
