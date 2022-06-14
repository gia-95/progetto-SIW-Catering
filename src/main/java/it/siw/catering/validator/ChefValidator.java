package it.siw.catering.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.siw.catering.model.Buffet;
import it.siw.catering.model.Chef;

@Component
public class ChefValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Chef.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Chef chef = (Chef) target;
		
		if (chef.getNome() =="" ) {
			errors.reject("chef.nomeVuoto");
		}
		if( chef.getCognome() == "" ) {
			errors.reject("chef.cognomeVuoto");
		}
		
	}

}
