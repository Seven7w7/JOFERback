package TiendaJOFER.service;

import TiendaJOFER.model.Invoice;
import TiendaJOFER.model.Product;
import TiendaJOFER.repository.InvoiceRepository;
import TiendaJOFER.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    public Invoice createInvoice(String empleado, List<Long> productIds, double total, double abono) {
        // Generar número de factura secuencial
        String numeroFactura = "Factura de venta # " + String.format("%07d", (int) (Math.random() * 10000000));

        double saldoRestante = total - abono;

        // Obtener productos desde la base de datos
        List<Product> productosVendidos = productRepository.findAllById(productIds);

        // Crear la factura
        Invoice invoice = new Invoice();
        invoice.setNumeroFactura(numeroFactura);
        invoice.setFecha(LocalDateTime.now());
        invoice.setEmpleado(empleado);
        invoice.setProductos(productosVendidos);  // Asocia los productos vendidos
        invoice.setTotal(total);
        invoice.setAbono(abono);
        invoice.setSaldoRestante(saldoRestante);

        // Guardar la factura
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
