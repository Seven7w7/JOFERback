package TiendaJOFER.controller;

import TiendaJOFER.model.Product;
import TiendaJOFER.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Crear un nuevo producto
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    // Obtener todos los productos
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // Vender productos (descontar stock y registrar venta)
    @PostMapping("/sell")
    public Product sellProduct(@RequestBody ProductController.SellRequest sellRequest) {
        return productService.sellProduct(sellRequest.getProductId(), sellRequest.getCantidad());
    }


    // Clase para representar la solicitud de venta
    public static class SellRequest {
        private Long productId;
        private int cantidad;

        // Getters y setters
        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
    }
}
