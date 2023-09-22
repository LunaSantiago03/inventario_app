package micrud.demo.Repositories;

import micrud.demo.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select product_id from product_category where category_id = :categoryId",nativeQuery = true)
    public List<Integer> getCategoryProducts(Integer categoryId);
}
