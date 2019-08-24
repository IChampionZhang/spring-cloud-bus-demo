package com.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

	@Value("${name}")
	private String name;
	
	@Value("${age}")
	private int age;
	
	@Value("${password}")
	private String password;
	
	@GetMapping("consumer")
    public Map<String,Object> getConsumer(){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("age",age);
        map.put("password",password);
        return map;
    }
	
	@PostMapping("/find-user")
    public String getName(@RequestBody Map<String,Object> map){
        return (String) map.get("name");
    }
	
	@GetMapping("user")
    public String getName(@RequestParam("name") String name){
        return name;
    }
}
