package novoprojeto.service;

import novoprojeto.model.Empresa;
import novoprojeto.repository.Empresas;
import novoprojeto.util.Transacional;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroEmpresaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Empresas empresas;

    @Transacional
    public void salvar(Empresa empresa){
        empresas.guardar(empresa);
    }

    @Transacional
    public void excluir(Empresa empresa){
        empresas.remover(empresa);
    }

}
