package com.example.ManejoMensajes.web;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /*Para manejar el concepto de internacionalizacion
    * vamos a usar una clase de la interface implementada
    * llamada addInterceptors.*/

    @Bean
    public LocaleResolver localeResolver (){
        /*Con este metodo especificaxmos el lenguaje que queremos manejar por default
        * que en nuestro caso seria el español*/
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }

    /*ahora se configura un metodo para que podamos
    * cambiar el idioma de manera dinamica*/

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        /*creamos una variable que nos va a servir de parametro
        * para poder cambiar nuestro lenguaje
        * */
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /*Por ultimo registraremos este interceptor con el siguiente metodo
    el cual sobre escribiremos por lo que agregamos la etiqueta de @Override
    * para que no me tire error esta clase debemos de configurar
    * con la anotacion @Configuration la clase webConfig para que
    * se registren cada uno de los @Bean*/
    @Override
   public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }
}
