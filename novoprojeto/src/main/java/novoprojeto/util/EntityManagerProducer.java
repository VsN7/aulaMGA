package novoprojeto.util;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

    private EntityManagerFactory factory;

    public EntityManagerProducer(){
        this.factory = Persistence.createEntityManagerFactory("AlgaWorksPU");
    }

    @Produces
    @javax.enterprise.context.RequestScoped
    public EntityManager createEntityManager(){
        return this.factory.createEntityManager();
    }

    public void closeEntityManager(@Disposes EntityManager manager){
        manager.close();
    }

}
