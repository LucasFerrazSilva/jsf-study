package com.ferraz.erp.id;

import java.math.BigDecimal;

import javax.inject.Inject;

public class RelatorioService {

	@Inject
	private Relatorios relatorios;
	
	public BigDecimal getTotal() {
		return this.relatorios.total();
	}
	
}
