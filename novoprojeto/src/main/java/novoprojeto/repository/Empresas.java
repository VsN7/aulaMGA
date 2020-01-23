package novoprojeto.repository;

import novoprojeto.model.Empresa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class Empresas implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager manager;

    public Empresas(){

    }

    @Inject
    public Empresas(EntityManager manager){

    }

    public Empresa porId(Long id){
        return manager.find(Empresa.class, id);
    }
    public List<Empresa> pesquisar(String nome){
        Query query = manager.createQuery("from empresa where nomeFantasia like :nomeFantasia");
        query.setParameter("nomeFantasia", nome + "%");
        return query.getResultList();
    }
    public Empresa guardar(Empresa empresa){
        return manager.merge(empresa);
    }
    public void remover(Empresa empresa){
        empresa = porId(empresa.getId());
        manager.remove(empresa);
    }

}
