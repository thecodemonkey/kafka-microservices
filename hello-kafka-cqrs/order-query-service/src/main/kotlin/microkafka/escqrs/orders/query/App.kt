package microkafka.escqrs.orders.query

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
    print("start order query microservice...")
}
