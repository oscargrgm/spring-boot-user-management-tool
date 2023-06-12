package net.javaguides.springboot

import io.swagger.v3.oas.annotations.ExternalDocumentation
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(
	info = Info(
		title = "Spring Boot REST API Documentation",
		description = "Spring Boot REST API Documentation.",
		version = "0.0.1-SNAPSHOT",
		contact = Contact(
			name = "Óscar Grande",
			email = "oscargrgm@gmail.com",
			url = "https://github.com/oscargrgm/spring-boot-user-management-tool"
		),
		license = License(
			name = "Apache 2.0",
			url = "https://github.com/oscargrgm/spring-boot-user-management-tool/blob/main/LICENSE"
		)
	),
	externalDocs = ExternalDocumentation(
		description = "Spring Boot User Management Documentation"
	)
)
class SpringbootRestfulWebservicesApplication

fun main(args: Array<String>) {
	runApplication<SpringbootRestfulWebservicesApplication>(*args)
}
