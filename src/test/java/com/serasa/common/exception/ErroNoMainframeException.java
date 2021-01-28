package com.serasa.common.exception;

import java.io.Serializable;
/**
 * @author Thiago Gaspar Levin
 */
public class ErroNoMainframeException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7692974420741605185L;

    public ErroNoMainframeException() {
        super("Erro no mainframe!");
    }
}
