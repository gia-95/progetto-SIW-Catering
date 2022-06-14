package it.siw.catering.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.siw.catering.model.Buffet;
import it.siw.catering.model.Piatto;

@Component
public class PiattoController implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Piatto.class.equals(clazz);

	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Piatto piatto = (Piatto) target;
		
		if(piatto.getNome() == "") {
			errors.reject("piatto.nomeVuoto");
		}
		
		if(piatto.getDescrizione() == "") {
			errors.reject("piatto.descVuoto");
		}
		
	}
	
	

}
