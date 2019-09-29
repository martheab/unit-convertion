package tdt4250.conversion.api;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface ConversionUnit {
	String getCurrentUnit();
	List<String> getConversionFormulas();
}
