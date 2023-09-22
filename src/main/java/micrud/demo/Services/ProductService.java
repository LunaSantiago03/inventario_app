package micrud.demo.Services;


import micrud.demo.Models.Category;
import micrud.demo.Models.Product;
import micrud.demo.Repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Service
public class ProductService {
    @Autowired
    IProductRepository productRepository;
    public ProductService(IProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public ResponseEntity saveProduct(Product pro){
        try{
            productRepository.save(pro);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con el ID: " + id));
    }
    public ResponseEntity updateById(Product product, Integer id){
        try{
            Product p = productRepository.findById(id).get();
            p.setCantidad(product.getCantidad());
            p.setId(product.getId());
            p.setCategories(product.getCategories());
            p.setNombre(product.getNombre());
            productRepository.save(p);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity deleteProductById(Integer id){
        try{
            productRepository.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}