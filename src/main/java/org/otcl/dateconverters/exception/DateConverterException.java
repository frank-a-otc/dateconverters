/**
*
* @author  Franklin Abel (Joshua), 
* @version 1.0
* @since   2020-09-10 
*/
package org.otcl.dateconverters.exception;

public class DateConverterException extends RuntimeException {

	private static final long serialVersionUID = 6218106303823662498L;
	private String errorCode;

	public DateConverterException(String errorCode) {
		this.errorCode = errorCode;
	}

	public DateConverterException(String errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	public DateConverterException(Throwable cause) {
		super(cause);
	}

	public DateConverterException(String errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public DateConverterException(String errorCode, String msg, Throwable cause) {
		super(msg, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
