/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.thinkme.sys.user.exception;

public class UserPasswordNotMatchException extends UserException {

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
