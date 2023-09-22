package micrud.demo.Controllers;

import micrud.demo.Models.Category;
import micrud.demo.Models.ProductDTO;
import micrud.demo.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/category")
@CrossOrigin(value = "http://localhost:4200")
public class CategoryController {
    @Autowired
    private CategoryService cs;

    @GetMapping("")
    public List<Category> getAll(){
        return cs.getCategories();
    }

    @PostMapping("")
    public ResponseEntity saveCategory(@RequestBody Category c){
        cs.saveCategory(c);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoriaById(@PathVariable Integer id) {
        Category categoria = cs.getCategoryById(id);
        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(path = "/{id}/update")
    public ResponseEntity updateCategory(@RequestBody Category c,@PathVariable Integer id){
        return cs.updateById(c,id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoryById(@PathVariable Integer id) {
        return cs.deleteCategoryById(id);
    }

    @GetMapping("/{id}/getCategoriesProducts")
    public List<ProductDTO> getCategoriesProducts(@PathVariable Integer id){
        return cs.getCategoriesProducts(id);
    }
}
