package jm.ac.servicio;

import jm.ac.modelo.Contacto;

import java.util.List;

public interface IContactoServicio {
    // Metodo para listar todos los obj de tipo contacto
    public List<Contacto> listarContactos();

    public Contacto  buscarContactoPorId(Integer idContacto);

    public Contacto guardarContacto(Contacto contacto);

    public void eliminarContacto(Contacto contacto);
}
