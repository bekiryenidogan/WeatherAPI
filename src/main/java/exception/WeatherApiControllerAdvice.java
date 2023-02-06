package exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@ControllerAdvice
public class WeatherApiControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(WeatherApiControllerAdvice.class);

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorMessage> handleWithCityName(HttpClientErrorException httpClientErrorException){
        logger.warn("Returning HTTP 400 Bad Request, Please be careful!", httpClientErrorException);
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                httpClientErrorException.getMessage()
        );
        return  new ResponseEntity(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> handleWithNullPointer(
            NullPointerException nullPointerException) {
        logger.warn("Returning Null Pointer Exception, Please be careful!", nullPointerException);
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_ACCEPTABLE.value(),
                new Date(),
                nullPointerException.getMessage());


        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ErrorMessage> handleWithUnauthorized(HttpClientErrorException.Unauthorized unauthorized) {
        logger.warn("Returning HTTP 401 Unauthorized Error, Please be sure your api key!", unauthorized);
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.UNAUTHORIZED.value(),
                new Date(),
                unauthorized.getMessage());

        return new ResponseEntity(errorMessage, HttpStatus.UNAUTHORIZED);
    }
}
