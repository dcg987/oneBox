package servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import repositorio.Products;

@Service
@Repository
public class ProductService implements InitializingBean {

	private List<Products> repository;

	@Autowired
    private CacheManager cacheManager;
	
	@CachePut(value = "newProduct")
	public void insert(Products product) {
		System.out.println("Servicio insert: " + product.id + " " + product.name + " " + product.amount);
		this.repository.add(product);
	}

	@Cacheable(value = "newProduct")
	public List<Products> load() {
		for(int i = 0; i < this.repository.size(); i++) {
			System.out.println("Service Load: -> ID " + this.repository.get(i).id 
											 + " Name " + this.repository.get(i).name
											 + " Amount " + this.repository.get(i).amount);			
		}
		return this.repository;
	}
	
	@CacheEvict(cacheNames = "newProduct")
    public Products deleteOneProductCache(Products pDelete) {

        Products product = pDelete;
        
        for (Products p : this.repository) {
            if (p.equals(product)) {
                this.repository.remove(p);
                System.out.println("Service Delete: -> ID " + p.id 
						 + " Name " + p.name
						 + " Amount " + p.amount);	
                return p;
            }
        }
        return null;
    }
	
	public void deleteCache() {
		if(cacheManager.getCache("newProduct") !=null) {
	         System.out.println("Eliminamos la cache de java");
	         cacheManager.getCache("newProduct").clear();
	         System.out.println("Eliminamos los datos de la lista");
	         this.repository.clear();
	     }
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.repository = new ArrayList<>();
	}

}
