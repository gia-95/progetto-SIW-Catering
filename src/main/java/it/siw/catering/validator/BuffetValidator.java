package it.siw.catering.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.siw.catering.model.Buffet;

@Component
public class BuffetValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Buffet buffet = (Buffet) target;
		
		if( buffet.getNome() == "" ) {
			errors.reject("buffet.nomeVuoto");
		}
		if( buffet.getDescrizione() == "" ) {
			errors.reject("buffet.descVuoto");
		}
		
		
	}

}
