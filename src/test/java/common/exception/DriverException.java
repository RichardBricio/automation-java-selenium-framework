package common.exception;

import common.drivers.DriverType;


import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @author Thiago Gaspar Levin
 */
public class DriverException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -5186732370535320272L;
    final static Logger logger = LogManager.getLogger(DriverException.class);

    public DriverException(DriverType type) {
        super("Erro ao pegar o driver " + type);
    }
}
