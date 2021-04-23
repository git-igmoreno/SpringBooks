package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class MainController {
	
	@RequestMapping("health")
	ResponseEntity <String> apiHealth(){
		return new ResponseEntity<String>("All services are online", HttpStatus.OK);
	}
	@RequestMapping("version")
	ResponseEntity <String> apiVersion(){
		return new ResponseEntity<String>("Newest version is v1", HttpStatus.OK);
	}

}
