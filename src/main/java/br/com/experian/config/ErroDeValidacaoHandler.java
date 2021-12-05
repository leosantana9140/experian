package br.com.experian.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.experian.dto.ErroDeFormularioDTO;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDTO> handle(MethodArgumentNotValidException methodArgumentNotValidException) {
		List<ErroDeFormularioDTO> erros = new ArrayList<ErroDeFormularioDTO>();
		List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();

		fieldErrors.stream().forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioDTO erroDeFormularioDTO = new ErroDeFormularioDTO(e.getField(), mensagem);
			erros.add(erroDeFormularioDTO);
		});
		
		return erros;
	}
}
