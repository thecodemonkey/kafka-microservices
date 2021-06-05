package microkafka.escqrs.orders.query

import microkafka.escqrs.orders.query.model.toDTO
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(val orderRepository: OrderRepository) {

    @GetMapping
    fun getAllOrders() =
        ok().body(orderRepository.findAll().toDTO())

}
