package modelo.entidades;

import java.util.Date;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-02-25T23:26:31")
@StaticMetamodel(Actividad.class)
public class Actividad_ { 

    public static volatile SingularAttribute<Actividad, String> descripcion;
    public static volatile SingularAttribute<Actividad, Date> fecha;
    public static volatile SingularAttribute<Actividad, List> imagenes;
    public static volatile SingularAttribute<Actividad, String> titulo;
    public static volatile SingularAttribute<Actividad, Long> id;

}