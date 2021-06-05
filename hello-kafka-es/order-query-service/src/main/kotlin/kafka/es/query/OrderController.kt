package kafka.es.query

import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(val repository: OrderRepository) {

    @GetMapping
    fun getAllOrders() =
        ok().body(repository.findAll())

}
