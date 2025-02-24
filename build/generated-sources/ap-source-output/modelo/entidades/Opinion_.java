package modelo.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2025-02-24T17:41:15")
@StaticMetamodel(Opinion.class)
public class Opinion_ { 

    public static volatile SingularAttribute<Opinion, String> contenido;
    public static volatile SingularAttribute<Opinion, ExperienciaViaje> experiencia;
    public static volatile SingularAttribute<Opinion, Usuario> usuario;
    public static volatile SingularAttribute<Opinion, Long> id;

}