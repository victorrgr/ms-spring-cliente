package br.cs.mscliente.standard;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public abstract class StandardController {

    private final SimpleDateFormat formatter;

    public StandardController() {
        this.formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:ms'Z'");
        this.formatter.setTimeZone(TimeZone.getTimeZone("Brazil/East"));
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<StandardResponseError> handleException(HttpServletRequest request, Exception exception) {
        var response = new StandardResponseError();
        response.setException(exception.toString().split(":")[0]);
        response.setMethod(request.getMethod());
        response.setPath(request.getRequestURI());
        response.setTimestamp(this.formatter.format(new Date()));

        if (exception instanceof IllegalArgumentException iae) {
            response.setMessage(iae.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        } else if (exception instanceof RestClientException rce) {
            response.setMessage("Houve um erro de comunicação com outro serviço");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.internalServerError().body(response);
        } else if (exception instanceof HttpMessageNotReadableException) {
            response.setMessage("JSON enviado em formato inválido.");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.internalServerError().body(response);
        }

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(exception.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

}
