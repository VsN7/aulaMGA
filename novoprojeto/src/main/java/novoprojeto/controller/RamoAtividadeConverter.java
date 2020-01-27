package novoprojeto.controller;

import novoprojeto.model.RamoAtividade;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;


public class RamoAtividadeConverter implements Converter {


    private List<RamoAtividade> listaRamoAtividades;

    public RamoAtividadeConverter() {

    }

    public RamoAtividadeConverter(List<RamoAtividade> listaRamoAtividades) {
        this.listaRamoAtividades = listaRamoAtividades;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null){
            return null;
        }else{
            Long id = Long.valueOf(value);
            for (RamoAtividade ramoAtividade: listaRamoAtividades){
                if(id.equals(ramoAtividade.getId())){
                    return ramoAtividade;
                }
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent Component, Object value) {
        try{
        if(value == null) {
            return null;
        }
        RamoAtividade ramoAtividade = (RamoAtividade) value;
        return ramoAtividade.getId()+"";

    }catch (Exception  e){
            return null;
        }
        }
}
