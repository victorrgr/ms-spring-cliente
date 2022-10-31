package br.localizacao.msendereco.standard;

import br.localizacao.msendereco.modelo.UnidadeFederativa;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
        } else if (exception instanceof HttpMessageNotReadableException hmn) {
            response.setMessage("JSON enviado em formato inválido.");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            if (hmn.getCause() instanceof InvalidFormatException ife &&
                ife.getTargetType().equals(UnidadeFederativa.class))
                response.setMessage("Unidade Federativa informada incorretamente.");
            return ResponseEntity.badRequest().body(response);
        }

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(exception.getMessage());
        return ResponseEntity.internalServerError().body(response);
    }

}
