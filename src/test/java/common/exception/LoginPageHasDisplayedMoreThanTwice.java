package common.exception;

import java.io.Serializable;
/**
 * @author Thiago Gaspar Levin
 */
public class LoginPageHasDisplayedMoreThanTwice extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -2193807747952775543L;

    public LoginPageHasDisplayedMoreThanTwice() {
        super("Login page has displayed more than twice");
    }
}
