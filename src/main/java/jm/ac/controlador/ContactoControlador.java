package jm.ac.controlador;

import jm.ac.servicio.ContactoServicio;
import jm.ac.servicio.IContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ac-app")
@CrossOrigin(value = "http://localhost:3000")
public class ContactoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ContactoControlador.class);

    @Autowired
    private IContactoServicio contactoServicio;
}
