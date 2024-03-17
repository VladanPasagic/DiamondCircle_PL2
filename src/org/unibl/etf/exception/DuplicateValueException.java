package org.unibl.etf.exception;

public final class DuplicateValueException extends Exception{
    public DuplicateValueException()
    {
        super("Name got repeated");
    }

}
