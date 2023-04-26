package com.catalogo.productos.app;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.catalogo.productos.app.dao.CategoriaDao;
import com.catalogo.productos.app.dao.FacturaDao;
import com.catalogo.productos.app.dao.ImagenDao;
import com.catalogo.productos.app.dao.ProductoCarritoDao;
import com.catalogo.productos.app.dao.ProductoDao;
import com.catalogo.productos.app.dao.RolDao;
import com.catalogo.productos.app.dao.UsuarioDao;
import com.catalogo.productos.app.model.Categoria;
import com.catalogo.productos.app.model.Producto;
import com.catalogo.productos.app.model.Roles;
import com.catalogo.productos.app.model.Usuario;
import com.catalogo.productos.app.service.util.enu.ERoles;

@SpringBootApplication
public class AppCatalogoApplication implements CommandLineRunner {

	@Autowired
	private ProductoDao productoDao;

	@Autowired
	private CategoriaDao categoriaDao;
	
	@Autowired
	private ImagenDao imagenDao;
	
	@Autowired
	private FacturaDao facturaDao;
	
	@Autowired
	private RolDao roleDao;
	
	@Autowired
	private ProductoCarritoDao productoCarritoDao;
	
	@Autowired
	private UsuarioDao usuarioDao;

	public static void main(String[] args) {
		SpringApplication.run(AppCatalogoApplication.class, args);
	}
	
	private void createUserAndRole () {
		if(roleDao.count()==0) {
			roleDao.saveAll(Arrays.asList(new Roles(ERoles.ROLE_USER.toString()),
								new Roles(ERoles.ROLE_ADMIN.toString()),
								new Roles(ERoles.ROLE_MODERADOR.toString())));
	
		}
		if(usuarioDao.count()==0) {
			 Calendar calendar = Calendar.getInstance();
			   calendar.set(Calendar.YEAR, 1997);
			   calendar.set(Calendar.MONTH, 1);
			   calendar.set(Calendar.DATE, 1);
			   Date date = calendar.getTime();
			   
			   Set<Roles> roles = new HashSet<>();
			   roles.add(this.roleDao.findByRole(ERoles.ROLE_ADMIN.toString()).get());
			   
			usuarioDao.save(new Usuario("kokyvr"
					,new BCryptPasswordEncoder().encode("password")
					,date
					,roles,"venturakoky12@gmail.com","942727995"));
		}
		
	}

	@Override
	public void run(String... args) throws Exception {
		categoriaDao.deleteAll();
		productoDao.deleteAll();
		imagenDao.deleteAll();
		facturaDao.deleteAll();
		productoCarritoDao.deleteAll();
		Categoria categoria1 = new Categoria("Electrodomestico");
		Categoria categoria2 = new Categoria("Tecnologia");
		categoriaDao.saveAll(Arrays.asList(categoria1, categoria2));

		Categoria categoriaBD = categoriaDao.findByNombre("Tecnologia").get();

		productoDao.save(new Producto("MSI Monitor", 1140.50, 1, categoriaBD));
		
		createUserAndRole();
	}

}
