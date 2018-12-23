package com.partsshop.rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.partsshop.rest.model.Car;
import com.partsshop.rest.model.PartsCategory;
import com.partsshop.rest.model.User;
import com.partsshop.rest.model.UserRoles;
import com.partsshop.rest.repo.CarRepo;
import com.partsshop.rest.repo.PartsCategoryRepo;
import com.partsshop.rest.repo.UserRepo;
import com.partsshop.rest.utility.ActivationCodeGenerator;

@SpringBootApplication
public class PartsShopRestApplication implements CommandLineRunner {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PartsCategoryRepo partsCategoryRepo ; 
	@Autowired 
	private CarRepo carRepo ; 

	public static void main(String[] args) {
		SpringApplication.run(PartsShopRestApplication.class, args);
	}

	private void addParts() throws Exception {
		File f = new File("/Users/alaaabuzaghleh/Documents/spring-workspace/parts-shop-rest-v3/Shop-Parts/RowData/Parts_shop_cat.txt") ; 
		BufferedReader br = new BufferedReader(new FileReader(f)) ; 
		
		for(String line; (line = br.readLine()) != null; ) {
			PartsCategory pc = new PartsCategory() ; 
			pc.setName(line);
			this.partsCategoryRepo.save(pc); 
		}
		
		br.close(); 

	}
	
	private void addCars() throws Exception {
		File f = new File("/Users/alaaabuzaghleh/Documents/spring-workspace/parts-shop-rest-v3/Shop-Parts/RowData/CarsList_1.txt") ; 
		BufferedReader br = new BufferedReader(new FileReader(f)) ; 
		
		for(String line; (line = br.readLine()) != null; ) {
			String [] carInfArr = line.split(":") ; 
			System.out.println("Make " + carInfArr[0]);
			String make = carInfArr[0] ; 
			String [] models = carInfArr[1].split(",")  ;
			for(String model : models) {
				System.out.println("Model " + model);
				Car c = new Car() ; 
				c.setMake(make); 
				c.setModel(model); 
				this.carRepo.save(c) ; 
			}
		}
		
		br.close(); 

	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		Locale.setDefault(Locale.US);
		messageSource.setBasename("classpath:i18n/messages");
		messageSource.setCacheSeconds(3600); // Refresh cache once per hour.
		return messageSource;
	}

	public void run(String... args) throws Exception {
		this.userRepo.deleteAll();
		List<UserRoles> roles = new ArrayList<UserRoles>();
		roles.add(new UserRoles("ROLE_ADMIN"));
		User u = new User("Alaa", "Abuzaghleh", "alaa.abuzaghleh@gmail.com", passwordEncoder().encode("12345678"),
				false, roles);
		this.userRepo.save(u);

		Optional<User> user = this.userRepo.findById(u.getId());
		if (user.isPresent()) {
			System.out.println(user.get().toString());
		}
		
		System.out.println("Start add cars");
		//addCars(); 
		System.out.println("End add cars");
		
		System.out.println("Start add parts category");
		//addParts(); 
		System.out.println("End add parts category");
		
		
	}
	
	
}
