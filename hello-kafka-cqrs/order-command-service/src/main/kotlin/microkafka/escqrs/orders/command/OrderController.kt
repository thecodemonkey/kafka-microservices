package microkafka.escqrs.orders.command

import microkafka.escqrs.orders.command.model.OrderAmountUpdateCommand
import microkafka.escqrs.orders.command.model.OrderCreateCommand
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
    val orderCommandHandler: OrderCommandHandler
) {
    @PostMapping
    fun newOrder(@RequestBody command: OrderCreateCommand) =
        ok().body(orderCommandHandler.handleCreate(command))

    @PutMapping
    fun updateOrder(@RequestBody command: OrderAmountUpdateCommand) =
        ok().body(orderCommandHandler.handleUpdate(command))
}
