package micrud.demo.Services;
import micrud.demo.Models.Category;
import micrud.demo.Models.Product;
import micrud.demo.Models.ProductDTO;
import micrud.demo.Repositories.ICategoryRepository;
import micrud.demo.Repositories.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class CategoryService {
    @Autowired
    ICategoryRepository categoryRepository;
    IProductRepository productRepository;
    private final ModelMapper mm = new ModelMapper();

    public CategoryService(ICategoryRepository cr,IProductRepository pr){
        this.categoryRepository = cr;
        this.productRepository = pr;
    }
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public ResponseEntity saveCategory(Category c){
        try{
            categoryRepository.save(c);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria no encontrado con el ID: " + id));
    }

    public ResponseEntity updateById(Category c, Integer id){
        try{
            Category cc = categoryRepository.findById(id).get();
            cc.setId(c.getId());
            cc.setNombre(c.getNombre());
            cc.setProducts(c.getProducts());
            categoryRepository.save(cc);
            return ResponseEntity.status(OK).build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity deleteCategoryById(Integer id){
        try{
            categoryRepository.deleteById(id);
            return ResponseEntity.status(OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<ProductDTO> getCategoriesProducts(Integer categoryId){
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (!category.isPresent()) {
            throw new NoSuchElementException("Categoría no encontrada con el ID: " + categoryId);
        }else{
            List<ProductDTO> productList = new ArrayList<>();
            List<Integer> cp = categoryRepository.getCategoryProducts(categoryId);
            for(int i = 0; i < cp.size(); i++){
                productList.add(mm.map(productRepository.findById(cp.get(i)).get(),ProductDTO.class));
            }
            return productList;
        }
    }

}
