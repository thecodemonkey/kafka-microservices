package microkafka.orders

import microkafka.orders.model.OrderDto
import microkafka.orders.model.toDTO
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
    val orderRepository: OrderRepository,
    val orderService: OrderService){

    @GetMapping
    fun getAllOrders() =
        ok().body(orderRepository.findAll().toDTO())

    @PostMapping
    fun newOrder(@RequestBody order: OrderDto) =
        ok().body(orderService.createOrder(order.userId!!, order.productId!!, order.amount!!))
}
