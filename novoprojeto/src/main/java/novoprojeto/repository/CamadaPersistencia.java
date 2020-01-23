package novoprojeto.repository;

import novoprojeto.model.Empresa;
import novoprojeto.model.RamoAtividade;
import novoprojeto.model.TipoEmpresa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class CamadaPersistencia {

    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AlgaWorksPU");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        //Declarando os repositorios
        RamoAtividades ramoAtividades = new RamoAtividades(em);
        Empresas empresas = new Empresas(em);

        //Buscando as informações do banco
        List<RamoAtividade> listaDeRamoAtividades = ramoAtividades.pesquisar("");
        List<Empresa> listaDeEmpresas = empresas.pesquisar("");
        System.out.println(listaDeEmpresas);

        //Criando uma empresa
        Empresa empresa = new Empresa();
        empresa.setNomeFantasia("João da Silva");
        empresa.setCnpj("41.952.519/0001-57");
        empresa.setRazaoSocial("João da Silva 41952519000157");
        empresa.setTipo(TipoEmpresa.MEI);
        empresa.setDataFundacao(new Date());
        empresa.setRamoAtividade(listaDeRamoAtividades.get(0));

        //salvando a empresa
        empresas.guardar(empresa);

        em.getTransaction().commit();

        //verificando se a inserção funcionou
        List<Empresa> listaDeEmpresas2 = empresas.pesquisar("");
        System.out.println(listaDeEmpresas2);

        Empresa ep = new Empresa();

        ep.setRazaoSocial("158248");
        ep.setNomeFantasia("nomeee");

        em.close();
        emf.close();

    }

}
