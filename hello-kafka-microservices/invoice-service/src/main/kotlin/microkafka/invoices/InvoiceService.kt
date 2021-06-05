package microkafka.invoices

import microkafka.invoices.model.Invoice
import microkafka.invoices.model.Order
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.*

@Service
class InvoiceService(val invoiceRepository: InvoiceRepository,
                     val kafkaTemplate: KafkaTemplate<String, Invoice>) {

    fun createInvoice(order: Order): Invoice {

        val invoice = Invoice().also {
            it.id = UUID.randomUUID().toString()
            it.orderId = order.id
            it.userId = order.userId
            it.amount = order.amount?.times(0.5) // possible implementation  sum of (productRepository.getProducts(order.productId's))
        }

        // In this example NO CQRS and NO event sourcing are implemented,
        // the invoice is immediately persisted in the local DB when it is created,
        // instead of receiving the corresponding event !
        invoiceRepository.save(invoice);

        kafkaTemplate.send("invoices", "create", invoice)

        return invoice;
    }
}
