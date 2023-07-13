package com.ferraz.erp.injecaodedependencias;

import java.math.BigDecimal;

import javax.inject.Inject;

public class RelatorioService {

	@Inject
	private Relatorios relatorios;
	
	public BigDecimal getTotal() {
		return this.relatorios.total();
	}
	
}
