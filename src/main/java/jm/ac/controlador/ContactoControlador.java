package jm.ac.controlador;

import jm.ac.exepcion.RecursoNoEncontradoExepcion;
import jm.ac.modelo.Contacto;
import jm.ac.servicio.IContactoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ac-app")
@CrossOrigin(value = "http://localhost:5173")
public class ContactoControlador {
    private static final Logger logger = LoggerFactory.getLogger(ContactoControlador.class);

    @Autowired
    private IContactoServicio contactoServicio;

    @GetMapping("/contactos")
    //Listar contactos
    public List<Contacto> obtenerContacto(){
        var contactos = contactoServicio.listarContactos();
        contactos.forEach((contacto -> logger.info(contacto.toString())));
        return contactos;
    }
    // Guardar
    @PostMapping("/contactos")
    public Contacto agregarContactos(@RequestBody Contacto contacto){
        logger.info("Empleado a agregar: " + contacto);
        return contactoServicio.guardarContacto(contacto);
    }

    //  Modificar
    @GetMapping("/contactos/{id}")
    public ResponseEntity<Contacto> obtenerContactoPorId(@PathVariable Integer id){
        Contacto contacto = contactoServicio.buscarContactoPorId(id);
        if (contacto == null)
            throw new RecursoNoEncontradoExepcion("No se encontro el empleado id: " + id);
        return ResponseEntity.ok(contacto);
    }

    //Actualizar
    @PutMapping("/contactos/{id}")
    public ResponseEntity<Contacto> actualizarContacto( @PathVariable Integer id, @RequestBody Contacto contactoActualizado) {

        Contacto contactoExistente = contactoServicio.buscarContactoPorId(id);

        if (contactoExistente == null) {
            throw new RecursoNoEncontradoExepcion(
                    "No se encontró el contacto con ID: " + id
            );
        }

        contactoExistente.setNombre(contactoActualizado.getNombre());
        contactoExistente.setApellido(contactoActualizado.getApellido());
        contactoExistente.setTelefono(contactoActualizado.getTelefono());
        contactoExistente.setEmail(contactoActualizado.getEmail());
        contactoExistente.setDireccion(contactoActualizado.getDireccion());

        Contacto contactoGuardado =
                contactoServicio.guardarContacto(contactoExistente);

        return ResponseEntity.ok(contactoGuardado);
    }
    @DeleteMapping("/contactos/{id}")
    public  ResponseEntity<Void> eliminarContacto(@PathVariable Integer id){
        Contacto contactoExistente = contactoServicio.buscarContactoPorId(id);
        if (contactoExistente == null) {
            throw new RecursoNoEncontradoExepcion(
                    "No se encontro el contacto con ID: " + id
            );
        }
        contactoServicio.eliminarContacto(contactoExistente);
        return ResponseEntity.noContent().build();
    }

}
