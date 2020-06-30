package br.mil.marinha.sisconvapi.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.mil.marinha.sisconvapi.dto.ProprietariosDTO;
import br.mil.marinha.sisconvapi.repositories.ProprietarioRepository;
import br.mil.marinha.sisconvapi.resource.exceptions.FieldMessage;

public class ProprietarioInsertValidator implements ConstraintValidator<ProprietarioInsert, ProprietariosDTO> {
	
	
	@Autowired
	ProprietarioRepository proprietarioRepository;
	@Override
	public void initialize(ProprietarioInsert ann) {
	}

	@Override
	public boolean isValid(ProprietariosDTO proprietarioDto, ConstraintValidatorContext context) {
		
		
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		
		
		
			
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
