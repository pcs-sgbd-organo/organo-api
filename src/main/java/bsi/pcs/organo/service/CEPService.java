package bsi.pcs.organo.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CEPService {

	public String verificaCep(String cep) throws JsonMappingException, JsonProcessingException {
	
		RestTemplate restTemplate = new RestTemplate();
		String viaCepUrl = "https://viacep.com.br/ws/" + cep + "/json";
		
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(viaCepUrl, String.class);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(response.getBody());
			JsonNode erro = root.path("erro");
			
			if(erro != null && erro.toString().equals("true")) {
				return null;
			}
			
			return response.getBody();
		} catch(Exception e) {
			return null;
		}
		
	}
}
