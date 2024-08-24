package com.example.ManejoMensajes.web;

import com.example.ManejoMensajes.Domain.Persona;
import com.example.ManejoMensajes.Service.IPersonaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControllerInicio {

/*Para inyectar cualquier dependencia para que sea manejada por nuestro contenedor debemos de
* agregar la anotacion @Autowired que lo que hace es inyectar la interface de IPersonaDato directamente
* para que pueda ser manejada por nuestor controlador*/

    @Autowired // -> Sirve para inyectar en nuestro controlador en este caso.
    private IPersonaService personaService; // -> Inyectamos nuestra capa Logica y no la directa a la base de datos.

    @GetMapping("/")

    public String inicio(Model model){

        var listaPersonas = personaService.listarPersonas();
        /*nuestro personaService va a usar los metodos de nuestra interface IPersonaService ya que
        * esos metodos estan implementados en la clase PersonaServiceImp
        * */
        log.info("Ejecutando la clase ControllerInicio");
        /*Listado de objetos de tipo personas "ListaPersonas" y variable a compartir -- listaPersonas --*/
      model.addAttribute("listaPersonas", listaPersonas);


        return "index";
    }

    //Agregar el pad de "Agregar"
    @GetMapping("/agregar")
    /*Para agregar una nueva persona le pasamos como argumento el objero persona
    * ya que spring automaticamente va a buscar en su "fabrica" el objeto persona
    * para poder agregarlo, si no lo encuentra entonces lo crea para poder agregarlo.*/
    public String agregar (Persona persona){

        /*en el return ponemos "modificar" porque nos va a redirigir a la vista
        * que usaremos para agregar este nuevo objeto y que tambien esta vista
        * va a realizar los 2 casos de uso osea "agregar" y "modificar",*/
        return "modificar";
    }


    /*Las validaciones las vamos a poner en el metodo de guardar antes de que se
    * incerte un campo erroneo, lo vamos a hacer con las etiquetas Valid entre los parentesis
    * y con la Etiqueta Errors que nos va a indicar el error.
    * Las Validaciones y los errores siempre deben estar juntos como se muestra entre los
    * parentesis.*/
    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errors){
        if (errors.hasErrors()){
            // si existe algun error que me retorne a la vista modificar
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }

    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        /*hacemos un return a la vista de "modificar*/
        return "modificar";
    }

    @GetMapping("/eliminar/{idPersona}")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        /*hacemos un return a la vista de "inicio"*/
        return "redirect:/";
    }

}
