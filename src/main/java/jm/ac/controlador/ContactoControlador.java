package jm.ac.controlador;

import jm.ac.modelo.Contacto;
import jm.ac.servicio.IContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ac-app")
@CrossOrigin(value = "http://localhost:5173")
public class ContactoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ContactoControlador.class);

    @Autowired
    private IContactoServicio contactoServicio;

    @GetMapping("/contactos")
    public List<Contacto> obtenerContacto(){
        var contactos = contactoServicio.listarContactos();
        contactos.forEach((contacto -> logger.info(contacto.toString())));
        return contactos;
    }
}
