package com.catalogo.productos.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.catalogo.productos.app.dao.CategoriaDao;
import com.catalogo.productos.app.dao.ProductoDao;
import com.catalogo.productos.app.model.Categoria;
import com.catalogo.productos.app.model.Producto;

@SpringBootApplication
public class AppCatalogoApplication implements CommandLineRunner {

	@Autowired
	private ProductoDao productoDao;

	@Autowired
	private CategoriaDao categoriaDao;

	public static void main(String[] args) {
		SpringApplication.run(AppCatalogoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		categoriaDao.deleteAll();
		productoDao.deleteAll();

		Categoria categoria1 = new Categoria("Electrodomestico");
		Categoria categoria2 = new Categoria("Tecnologia");
		categoriaDao.saveAll(Arrays.asList(categoria1, categoria2));

		Categoria categoriaBD = categoriaDao.findByNombre("Tecnologia").get();

		productoDao.save(new Producto("MSI Monitor", 1140.50, 1, categoriaBD));

	}

}
