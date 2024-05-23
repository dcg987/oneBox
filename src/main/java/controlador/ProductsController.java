package controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import repositorio.Products;
import servicio.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductsController {
	
	private final ProductService servicio;
	
	@Autowired
	public ProductsController(ProductService servicio) {
		super();
		this.servicio = servicio;
	}
	
	@PostMapping
	public void guardar(@RequestBody Products product) {
		this.servicio.insert(product);
	}
	
	@GetMapping
    public List<Products> cargar() {
		return servicio.load();
	}
	
	@GetMapping("/deleteCache")
	public void deleteCache() {
		servicio.deleteCache();
	}
	
	@PostMapping("/delete")
	public Products deleteOneProductCache(@RequestBody Products product) {
		return servicio.deleteOneProductCache(product);
	}
	
}