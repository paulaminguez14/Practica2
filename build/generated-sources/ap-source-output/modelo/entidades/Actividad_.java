package modelo.entidades;

import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.ExperienciaViaje;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-02-26T21:39:37")
@StaticMetamodel(Actividad.class)
public class Actividad_ { 

    public static volatile SingularAttribute<Actividad, String> descripcion;
    public static volatile SingularAttribute<Actividad, Date> fecha;
    public static volatile SingularAttribute<Actividad, List> imagenes;
    public static volatile SingularAttribute<Actividad, ExperienciaViaje> experiencia;
    public static volatile SingularAttribute<Actividad, String> titulo;
    public static volatile SingularAttribute<Actividad, Long> id;

}