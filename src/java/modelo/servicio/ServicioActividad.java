/*
 * ServicioActividad.
 */
package modelo.servicio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import modelo.entidades.Actividad;
import modelo.entidades.ExperienciaViaje;
import modelo.servicio.exceptions.NonexistentEntityException;

/**
 *
 * @author jose
 */
public class ServicioActividad implements Serializable {

    public ServicioActividad(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Actividad actividad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(actividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Actividad actividad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            actividad = em.merge(actividad);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = actividad.getId();
                if (findActividad(id) == null) {
                    throw new NonexistentEntityException("The actividad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Actividad actividad;
            try {
                actividad = em.getReference(Actividad.class, id);
                actividad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actividad with id " + id + " no longer exists.", enfe);
            }
            em.remove(actividad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Actividad> findActividadEntities() {
        return findActividadEntities(true, -1, -1);
    }

    public List<Actividad> findActividadEntities(int maxResults, int firstResult) {
        return findActividadEntities(false, maxResults, firstResult);
    }

    private List<Actividad> findActividadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Actividad.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Actividad> findActividadPorIdExperiencia(Long idExperiencia) {
        EntityManager em = getEntityManager();
        try {
            // Obtiene CriteriaBuilder para construir las consultas
            CriteriaBuilder cb = em.getCriteriaBuilder();

            // Crea un CriteriaQuery para la entidad Actividad
            CriteriaQuery<Actividad> cq = cb.createQuery(Actividad.class);

            // Define la ra�z de la consulta (la entidad Actividad)
            Root<Actividad> root = cq.from(Actividad.class);

            // Establece la condici�n para filtrar por idExperiencia en la relaci�n con ExperienciaViaje
            Join<Actividad, ExperienciaViaje> join = root.join("experiencia"); 
            Predicate predicate = cb.equal(join.get("id"), idExperiencia);
            cq.where(predicate);

            // Crea la consulta de la entidad con los par�metros de CriteriaQuery
            TypedQuery<Actividad> query = em.createQuery(cq);

            // Ejecuta la consulta y devuelve el resultado
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Actividad findActividad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Actividad.class, id);
        } finally {
            em.close();
        }
    }

    public int getActividadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Actividad> rt = cq.from(Actividad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
