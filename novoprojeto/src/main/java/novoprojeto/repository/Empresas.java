package novoprojeto.repository;

import novoprojeto.model.Empresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class Empresas implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory manager;

    public Empresas(){
        manager = CustomEntityManagerFactory.getInstance().getFactory();
    }

//    @Inject
//    public Empresas(EntityManager manager){
//        this.manager = manager;
//    }

    public Empresa porId(Long id){
        return getEntityManager().find(Empresa.class, id);
    }

    private EntityManager getEntityManager() {
        return manager.createEntityManager();
    }

    public List<Empresa> pesquisar(String nome){
        Query query = getEntityManager().createQuery("select e from Empresa e where e.razaoSocial like :razaosocial");
        query.setParameter("razaosocial", nome + "%");
        return query.getResultList();
    }

    public List<Empresa> todas(){
        List<Empresa> from_empresa = getEntityManager().createQuery("select e from Empresa e order by e.id asc ", Empresa.class).getResultList();
        return from_empresa;

    }

    public void guardar(Empresa empresa)
    {

        EntityManager entityManager = getEntityManager();
        try {
            if(empresa.getId() != null){
                entityManager.getTransaction().begin();
                entityManager.merge(empresa);
                entityManager.getTransaction().commit();
            }else {
                entityManager.getTransaction().begin();
                entityManager.persist(empresa);
                entityManager.getTransaction().commit();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
//        return manager.merge(empresa);
    }

    public void remover(Empresa empresa){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            try {
                empresa = em.getReference(Empresa.class, empresa.getId());
                empresa.getId();
            } catch (EntityNotFoundException enfe) {
                System.out.println("The conta with id " + empresa.getId() + " no longer exists." + enfe);
            }
            em.remove(empresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
