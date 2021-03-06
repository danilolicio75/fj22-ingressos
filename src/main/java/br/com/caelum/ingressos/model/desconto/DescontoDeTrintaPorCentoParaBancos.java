package br.com.caelum.ingressos.model.desconto;

import java.math.BigDecimal;

public class DescontoDeTrintaPorCentoParaBancos implements Desconto{
	
	private BigDecimal percentualDeDesconto = new BigDecimal("0.3");
	
	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.subtract(trintaPorcentoSobre(precoOriginal));
	}

	private BigDecimal trintaPorcentoSobre(BigDecimal precoOriginal) {
		return precoOriginal.multiply(percentualDeDesconto);
	}
	
	@Override
	public String getDescricao() {
		return "Desconto Banco";
	}

}
