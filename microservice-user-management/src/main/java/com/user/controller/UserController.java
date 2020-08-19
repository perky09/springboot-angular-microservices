package com.user.controller;

import static com.user.model.Role.USER;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.model.User;
import com.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private Environment env;
	
	@Value("${spring.application.name}")
	private String serviceId;
	
	@GetMapping("/service/port")
	public String getPort(){
		return env.getProperty("local.server.port");
		
	}
	
	@GetMapping("/service/instances")
	public ResponseEntity<?> getInstances(){
		return new ResponseEntity(discoveryClient.getInstances(serviceId),HttpStatus.OK);
		
	}
	
	
	
	
	@GetMapping("/service/services")
	public ResponseEntity<?> getServices(){
		return new ResponseEntity(discoveryClient.getServices(),HttpStatus.OK);
		
	}
	
	
	//httpHeader,httpStatus,ResponseBody
	@PostMapping("/service/registration")
	public ResponseEntity<?> saveUser(@RequestBody User user) {		
		//username should be unique when adding a new user
		if(userService.getUserByUsername(user.getUsername()) != null)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
			user.setRole(USER);			
			return new ResponseEntity(userService.saveUser(user),HttpStatus.CREATED);
		}

	@GetMapping("/service/login")
	public ResponseEntity<?> getUser(Principal principal) {		
		//username should be unique when adding a new user
		if(principal == null || principal.getName() == null)
		{   //This means; logout will be successful. login?logout.
			return new ResponseEntity<>(HttpStatus.OK);
		}
						
			return ResponseEntity.ok(userService.getUserByUsername(principal.getName()));
		}
	@PostMapping("/service/names")
	public ResponseEntity<?> getNamesOfUsers(@RequestBody List<Long> idList){
		
		return ResponseEntity.ok(userService.listOfUserById(idList));
		
	}
	
	@GetMapping("/service/test")
	public ResponseEntity<?> test(){
		return ResponseEntity.ok("This Things Works Perfectly Fine...smile");
	}
	
	
	
}
