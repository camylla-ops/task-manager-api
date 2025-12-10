package br.com.oliveira.task_manager_api.handler;

import br.com.oliveira.task_manager_api.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

// Esta anotação diz ao Spring para aplicar este código a todos os Controllers
@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. Tratamento de Erros de Regra de Negócio/Segurança (400, 403, 404, etc.)
    // Captura qualquer exceção do tipo ResponseStatusException
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDTO> handleResponseStatusException(ResponseStatusException ex) {
        
        // Pega o status (ex: NOT_FOUND, FORBIDDEN, BAD_REQUEST)
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        
        // Constrói o DTO de resposta padronizado
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .status(status.value())
                .message(ex.getReason()) 
                .build();

        // Retorna o ResponseEntity com o status e o corpo de erro padronizado
        return new ResponseEntity<>(errorResponse, status);
    }
    
    
}