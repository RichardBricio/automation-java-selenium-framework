package common.exception;

import java.io.Serializable;

/**
 * @author Thiago Gaspar Levin
 */
public class DesbloqueioDeSenhaException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 745003341168660656L;

    public DesbloqueioDeSenhaException() {
        super("Desbloqueio com Alteração de Senha page");
    }
}
