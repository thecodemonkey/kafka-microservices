package kafka.es.command

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication


@SpringBootApplication
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
    print("start order command producer...")
}
