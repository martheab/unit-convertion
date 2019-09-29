package tdt4250.conversion.api;

import org.osgi.annotation.versioning.ConsumerType;

@ConsumerType
public class UnitConversionResult {
	
	private final float convertedValue;
	private final boolean success;
	private final String msg; 
	
	
	public UnitConversionResult(float convertedValue, boolean success, String msg) {
		this.convertedValue = convertedValue;
		this.success=success;
		this.msg=msg;
	
		
	}
	
	public float getConvertedValue() {
		return convertedValue;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public String getMessage() {
		return msg;
	}

}
