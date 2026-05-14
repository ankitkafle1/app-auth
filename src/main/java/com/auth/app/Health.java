package com.auth.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {

	@GetMapping("/1")
	public String hello() {
		return "200 OK";
	}
}
