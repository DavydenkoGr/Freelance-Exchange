package org.freelance.controllers;

import org.freelance.services.AbstractService;
import org.springframework.stereotype.Controller;

@SuppressWarnings("rawtypes")
public abstract class AbstractRegistrationController<T extends AbstractService> {
    protected T service;


}
