package kafka.es.query

import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(val repository: OrderRepository) {

    @GetMapping
    fun getAllOrders() = ok().body(repository.findAll())

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: String) = ok().body(repository.findById(id))

}
