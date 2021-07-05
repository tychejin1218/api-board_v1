package com.api.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/sample")
@RestController
public class SampleController {
	
	@GetMapping
	public String getSample() {
		return "sample";
	}
}