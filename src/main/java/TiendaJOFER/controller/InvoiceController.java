package TiendaJOFER.controller;

import TiendaJOFER.model.Invoice;
import TiendaJOFER.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // Crear una nueva factura
    @PostMapping
    public Invoice createInvoice(@RequestBody InvoiceRequest invoiceRequest) {
        return invoiceService.createInvoice(
                invoiceRequest.getEmpleado(),
                invoiceRequest.getProductIds(),
                invoiceRequest.getTotal(),
                invoiceRequest.getAbono()
        );
    }

    // Obtener todas las facturas
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
}

class InvoiceRequest {
    private String empleado;
    private List<Long> productIds;
    private double total;
    private double abono;

    // Getters y Setters
    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }
}
