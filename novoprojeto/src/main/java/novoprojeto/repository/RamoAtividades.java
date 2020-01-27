package novoprojeto.repository;

import novoprojeto.model.RamoAtividade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class RamoAtividades implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManagerFactory manager;

    public  RamoAtividades(){
        manager = CustomEntityManagerFactory.getInstance().getFactory();
    }

    public List<RamoAtividade> pesquisar(String descricao){
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        CriteriaQuery<RamoAtividade> criteriaQuery = criteriaBuilder.createQuery(RamoAtividade.class);

        Root<RamoAtividade> root = criteriaQuery.from(RamoAtividade.class);

        criteriaQuery.select(root);

        criteriaQuery.where(criteriaBuilder.like(root.get("descricao"), descricao + "%"));

        TypedQuery<RamoAtividade> query = manager.createEntityManager().createQuery(criteriaQuery);

        return query.getResultList();

    }

}
