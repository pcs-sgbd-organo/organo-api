package bsi.pcs.organo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import bsi.pcs.organo.service.CEPService;

@RestController
@RequestMapping("/cep")
public class CepController {
	
	@Autowired
	private CEPService cepService;

	@GetMapping("/verificaCep/{cep}")
	public ResponseEntity<?> verificaCep(@PathVariable(required = true) String cep) throws JsonMappingException, JsonProcessingException {
		
		String cepVerificado = this.cepService.verificaCep(cep);
		if(cepVerificado != null) {
			return ResponseEntity.status(HttpStatus.OK).body(cepVerificado);
		}
		return ResponseEntity.status(HttpStatus.OK).body("Cep não existe.");
	}
}
