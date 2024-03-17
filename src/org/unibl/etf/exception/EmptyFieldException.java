package org.unibl.etf.exception;

public final class EmptyFieldException extends Exception{

    public EmptyFieldException()
    {
        super("Field is empty");
    }

}
