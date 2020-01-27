package novoprojeto.repository;

import javax.persistence.Persistence;

public class CustomEntityManagerFactory {

    private static CustomEntityManagerFactory instance;

    private javax.persistence.EntityManagerFactory factory;



    private CustomEntityManagerFactory(){
         factory= Persistence.createEntityManagerFactory("AlgaWorksPU");
    }

    public static CustomEntityManagerFactory getInstance(){
        if(instance == null){
            instance = new CustomEntityManagerFactory();
        }
        return instance;
    }

    public javax.persistence.EntityManagerFactory getFactory() {
        return factory;
    }
}
