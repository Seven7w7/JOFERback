package TiendaJOFER.service;

import TiendaJOFER.model.Product;
import TiendaJOFER.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Guardar un producto
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Actualizar producto
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        product.setNombre(productDetails.getNombre());
        product.setColor(productDetails.getColor());
        product.setCantidad(productDetails.getCantidad());
        product.setPrecio(productDetails.getPrecio());
        product.setReferencia(productDetails.getReferencia());
        return productRepository.save(product);
    }

    // Eliminar producto
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Registrar venta de productos (descuenta la cantidad)
    public Product sellProduct(Long productId, int cantidadVendida) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Verificar que haya suficiente cantidad en stock
        if (product.getCantidad() < cantidadVendida) {
            throw new RuntimeException("Cantidad insuficiente en inventario");
        }

        // Descontar la cantidad vendida
        product.setCantidad(product.getCantidad() - cantidadVendida);

        // Registrar la fecha de la venta
        product.setFechaVenta(LocalDateTime.now());

        // Guardar el producto actualizado
        return productRepository.save(product);
    }
}
