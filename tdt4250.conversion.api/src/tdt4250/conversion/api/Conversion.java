package tdt4250.conversion.api;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface Conversion {
	String getCurrentUnit();
	String getRequestedUnit();
	float getValue(float inputValue);
}