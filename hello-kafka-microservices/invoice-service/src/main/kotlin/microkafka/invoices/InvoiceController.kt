package microkafka.invoices

import microkafka.invoices.model.toDTO
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/invoices")
class InvoiceController(val invoiceRepository: InvoiceRepository) {

    @GetMapping
    fun getAllInvoices() =
        ok().body(invoiceRepository.findAll().toDTO())

}
