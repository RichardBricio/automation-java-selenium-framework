package common.exception;

import java.io.Serializable;
/**
 * @author Thiago Gaspar Levin
 */
public class SomethingWrongException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 7472376961422824450L;

    public SomethingWrongException() {
        super("Something went wrong was displayed!");
    }
}
