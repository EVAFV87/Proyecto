package com.evastur.backend.dtos;

/**
 * Permite pasar la información necesaria a la aplicación para realizar un incremento o decremento de Stock.
 */
public class CambiaStockDto {
	
	/**
	 * Cantidad que se incrementará o decrementará el stock, independientemente de la operación el valor
	 * deberá ser siempre positivo.
	 */
	private int inc;
	
	public int getInc() {
		return inc;
	}
	
	public void setInc(int inc) {
		this.inc = inc;
	}

}
