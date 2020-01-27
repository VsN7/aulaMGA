package novoprojeto.controller;

import novoprojeto.model.Empresa;
import novoprojeto.model.RamoAtividade;
import novoprojeto.model.TipoEmpresa;
import novoprojeto.repository.Empresas;
import novoprojeto.repository.RamoAtividades;
import novoprojeto.service.CadastroEmpresaService;
import novoprojeto.util.FacesMessages;
import org.primefaces.context.RequestContext;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Empresas empresas;

    @Inject
    private RamoAtividades ramoAtividades;

    @Inject
    private CadastroEmpresaService cadastroEmpresaService = new CadastroEmpresaService();

    @Inject
    private FacesMessages messages;

    private List<Empresa> listaEmpresas;

    private String termoPesquisa;

    private Converter ramoAtividadeConverter;

    private Empresa empresa;

    public void prepararNovaEmpresa(){

        empresa = new Empresa();
    }

    public void prepararEdicao(){
        ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
    }

    public void excluir(){
        cadastroEmpresaService.excluir(empresa);

        empresa = null;

        this.atualizarRegistros();

        messages.info("Empresa excluída com sucesso!");
    }

    public void atualizarRegistros(){
        if(jaHouvePesquisa()){
            pesquisar();
        }else{
            todasEmpresas();
        }
    }

    public void salvar(){
        cadastroEmpresaService.salvar(empresa);
        this.atualizarRegistros();
        messages.info("Empresa salva com sucesso!");

        RequestContext.getCurrentInstance().update(Arrays.asList(
                "frm:empresasDataTable", "frm:messages"));
    }

    public void pesquisar(){

        listaEmpresas = empresas.pesquisar(termoPesquisa);

        if (listaEmpresas.isEmpty()) {
            messages.info("Sua consulta não retornou registros.");
        }

    }

    public void todasEmpresas(){
        empresas = new Empresas();
        listaEmpresas = empresas.todas();
    }

    public List<RamoAtividade> completarRamoAtividade(String termo){

        List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);
        ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);
        return listaRamoAtividades;
    }

    private boolean jaHouvePesquisa(){
        return termoPesquisa != null && !"".equals(termoPesquisa);
    }

    public List<Empresa> getListaEmpresas(){
        return listaEmpresas;
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public TipoEmpresa[] getTiposEmpresas(){
        return TipoEmpresa.values();
    }

    public Converter getRamoAtividadeConverter() {
        return ramoAtividadeConverter;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean isEmpresaSeleciona(){
        return empresa != null && empresa.getId() != null;
    }
}
