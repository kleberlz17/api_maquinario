package automacao.industrial.maquinario.exception;

import java.time.LocalDateTime;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Erros genéricos inesperados...
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroResponse> handleGenericException(Exception ex) {
		ErroResponse response = new ErroResponse(
				"Erro interno inesperado.",
				LocalDateTime.now(),
				HttpStatus.INTERNAL_SERVER_ERROR.value()
				);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}
	// Erros de validação com @Valid ex campos nulos. ou espaço exagerado.(fora de
	// faixa)S
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroResponse> handleValidationErros(MethodArgumentNotValidException ex) {
		String erros = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.joining("; "));

		ErroResponse response = new ErroResponse(erros, LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

	}
	// JSON MALFORMADO - exemplo, tem um String no campo ao invés de um numero(aqui
	// esperamos receber numero)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleJsonParseError(HttpMessageNotReadableException ex) {
		ErroResponse response = new ErroResponse(
				"Erro ao ler a requisição. Verifique o formato dos dados.",
				LocalDateTime.now(),
				HttpStatus.BAD_REQUEST.value()
				);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	// Erro pra caso digite um numero inválido. exemplo. dispositivo = 3
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErroResponse> handleIllegalArgument2(IllegalArgumentException ex) {
		ErroResponse response = new ErroResponse(ex.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
