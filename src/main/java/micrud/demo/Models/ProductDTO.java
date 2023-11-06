package micrud.demo.Models;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import micrud.demo.enums.Valoracion;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    @Id
    private Integer id;
    private String nombre;
    private Integer cantidad;
    private Double precio;
    private Boolean disponible;
    private Valoracion valoracion;
}
