package modelo.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.Actividad;
import modelo.entidades.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-02-25T23:26:31")
@StaticMetamodel(ExperienciaViaje.class)
public class ExperienciaViaje_ { 

    public static volatile SingularAttribute<ExperienciaViaje, String> descripcion;
    public static volatile SingularAttribute<ExperienciaViaje, Date> fechaInicio;
    public static volatile SingularAttribute<ExperienciaViaje, String> titulo;
    public static volatile SingularAttribute<ExperienciaViaje, Usuario> usuario;
    public static volatile SingularAttribute<ExperienciaViaje, Long> id;
    public static volatile SingularAttribute<ExperienciaViaje, Boolean> publicada;
    public static volatile ListAttribute<ExperienciaViaje, Actividad> actividades;

}