package common.exception;

import java.io.Serializable;
/**
 * @author Thiago Gaspar Levin
 */
public class WithoutAccessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3259853488454805900L;

    public WithoutAccessException() {
        super("User not authorized to login");
    }
}
