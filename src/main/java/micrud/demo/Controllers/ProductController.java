package micrud.demo.Controllers;


import micrud.demo.Models.Category;
import micrud.demo.Models.Product;
import micrud.demo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/product")
@CrossOrigin(value = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> getAll(){
        return this.productService.getProducts();
    }

    @PostMapping("")
    public ResponseEntity saveProduct(@RequestBody Product p){
        this.productService.saveProduct(p);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product p =productService.getProductById(id);
        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity updateProduct(@RequestBody Product p, @PathVariable Integer id){
        return this.productService.updateById(p,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        return productService.deleteProductById(id);
    }

}