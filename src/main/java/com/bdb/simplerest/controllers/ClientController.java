package com.bdb.simplerest.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bdb.simplerest.models.Client;
import com.bdb.simplerest.models.SimpleResponse;
import com.bdb.simplerest.repositories.IClientRepository;
import com.sun.istack.logging.Logger;


@Controller
@RequestMapping(path = "/clients")
@SuppressWarnings("finally")
public class ClientController extends AppController {
	
	private static final Logger Log = Logger.getLogger(ClientController.class);
	
	@Autowired
	protected IClientRepository repository;
	
	
	@GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SimpleResponse> findAll() {
		
		this.before();
				
		List<Client> listClient = null;
		
		try {
			
			listClient = (List<Client>) repository.findAll();
			if (listClient.isEmpty()) {
				this.status = HttpStatus.NOT_FOUND;
			}
			
		} catch(Exception ex) {
			this.exception = ex;
		} finally {
			return setResponse(listClient);
		}
		
	}
	
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<SimpleResponse> findById(@PathVariable("id") int id) {
		
		this.before();
				
		Client item = null;
		
		try {
			
			Optional<Client> opt = repository.findById(id);
			
			if (opt.isPresent()) {
				item = opt.get();				
			} else {
				this.status = HttpStatus.NOT_FOUND;
			}
			
		} catch(Exception ex) {
			this.exception = ex;
		} finally {
			return setResponse(item);
		}
		
	}
	
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SimpleResponse> create(@RequestBody Client item) {
		
		this.before();
						
		try {
			
			item = repository.save(item);
			this.status = HttpStatus.CREATED;
			
		} catch(Exception ex) {
			this.exception = ex;
		} finally {
			return setResponse(item);
		}
		
	}
	
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SimpleResponse> update(@PathVariable("id") int id, @RequestBody Client item) {
		
		this.before();
						
		try {
			item = repository.save(item);
		} catch(Exception ex) {
			this.exception = ex;
		} finally {
			return setResponse(item);
		}
		
	}
	
	
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SimpleResponse> delete(@PathVariable("id") int id) {
		
		this.before();
						
		try {
			repository.deleteById(id);
			Log.info("El registro con id: " + id + " se ha eliminado correctamente");
		} catch(Exception ex) {
			this.exception = ex;
		} finally {
			return setResponse(id);
		}
		
	}
	

}
