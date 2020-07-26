/**
 * 
 */
package com.digilytics.javaAssignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digilytics.javaAssignment.service.RoleService;



/**
 * @author hemant
 *
 */
@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;
	
		
	@PostMapping(value="/saverole")
	public ResponseEntity<String> saveRole(@RequestParam String name){
		Integer resultStatus = roleService.save(name);
		if(resultStatus == 201)
		return ResponseEntity.ok("created successfully.");
		else if(resultStatus == 403) {
			return ResponseEntity.badRequest().body("already exists.");
		}
		else
			return ResponseEntity.badRequest().body("Some thing is worng.");
		
	}
}
