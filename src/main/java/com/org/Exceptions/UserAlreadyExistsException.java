package com.org.Exceptions;

import com.org.model.User;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException (String message) { super(message); }
}
