package br.com.caelum.ingressos.model.desconto;

import java.math.BigDecimal;

public class DescontoEstudante implements Desconto{
	
	private BigDecimal metade = new BigDecimal(2.0);
	
	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.divide(metade);
	}

}
