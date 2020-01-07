/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.exception;

/**
 *
 * @author Muffin
 */
public class MyException extends RuntimeException {
	private int errorCode = 1;
	
	public MyException() {
	}
	
	public MyException(String msg){
		super(msg);
	}
	
	public MyException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
