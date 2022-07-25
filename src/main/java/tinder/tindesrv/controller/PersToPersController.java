package tinder.tindesrv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tinder.tindesrv.service.impl.PersToPersServiceImpl;
import tinder.tindesrv.service.impl.PersonServiceImpl;

@RestController
public class PersToPersController {
    private final PersonServiceImpl personService;
    private final PersToPersServiceImpl persToPersService;

    @Autowired
    public PersToPersController(PersonServiceImpl personService, PersToPersServiceImpl persToPersService) {
        this.personService = personService;
        this.persToPersService = persToPersService;
    }
}
