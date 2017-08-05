package br.com.caelum.ingressos.model.desconto;

import java.math.BigDecimal;

public interface Desconto {
	
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal);

}
