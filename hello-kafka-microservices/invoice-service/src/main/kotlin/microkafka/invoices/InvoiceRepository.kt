package microkafka.invoices

import microkafka.invoices.model.Invoice
import org.springframework.data.jpa.repository.JpaRepository

interface InvoiceRepository : JpaRepository<Invoice?, String?> {

}
