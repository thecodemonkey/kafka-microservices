package microkafka.invoices

import microkafka.invoices.model.Order
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class OrderListener(val invoiceService: InvoiceService) {

    // subscribes to the kafka topic "orders"
    // this function is called for every record of the stream that has not yet been processed
    @KafkaListener(topics= ["orders"])
    fun consume(order: Order) : Unit {
        println(" order received from orders topic : $order");

        // create a new invoice for each order received
        invoiceService.createInvoice(order);

        // received orders can also be saved in the local service DB for further processing
        // orderRepository.save(oder)
    }
}
