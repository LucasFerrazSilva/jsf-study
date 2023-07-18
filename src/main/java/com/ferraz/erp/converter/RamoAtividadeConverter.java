package com.ferraz.erp.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.ferraz.erp.model.RamoAtividade;

public class RamoAtividadeConverter implements Converter {
	
	private List<RamoAtividade> list;
	
	
	public RamoAtividadeConverter(List<RamoAtividade> list) {
		this.list = list;
	}
	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null)
			return null;
		
		Long id = Long.valueOf(value);
		
		for (RamoAtividade ramoAtividade: list) {
			if (id.equals(ramoAtividade.getId()))
				return ramoAtividade;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null)
			return null;
		
		RamoAtividade ramoAtividade = (RamoAtividade) value;
		
		return ramoAtividade.getId().toString();
	}

}
