package it.siw.catering.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.siw.catering.model.Ingrediente;
import it.siw.catering.model.Piatto;

@Component
public class IngredienteValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Piatto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Ingrediente ing = (Ingrediente) target;
		
		if (ing.getNome() == "") {
			errors.reject("ingrediente.nomeVuoto");
		}
		if (ing.getDescrizione() == "") {
			errors.reject("ingrediente.descVuoto");
		}
		
	}

}
