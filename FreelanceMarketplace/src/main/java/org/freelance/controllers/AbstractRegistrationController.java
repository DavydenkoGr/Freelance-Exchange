package org.freelance.controllers;

import org.springframework.stereotype.Controller;

public abstract class AbstractRegisterController<T extends AbstractService> {
    protected T service;

}
