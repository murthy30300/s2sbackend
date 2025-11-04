package com.klu.ss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CheckController
{
	 @GetMapping("/")
	    public String check() {
	        return "Working new good";
	    }
}
