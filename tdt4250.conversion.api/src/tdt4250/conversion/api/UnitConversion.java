package tdt4250.conversion.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class UnitConversion {

	private static final String DEFAULT_MESSAGE = "Sorry, unit not included in library.";
	private Collection<Conversion> conversions = new ArrayList<Conversion>();
	
	public void addConversion(Conversion conversion) {
		conversions.add(conversion);
	}
	
	public void removeConversion(Conversion conversion) {
		conversions.remove(conversion);
	}
	
	public UnitConversion(Conversion...conv) {
		conversions.addAll(Arrays.asList(conv));
	}
	
	public UnitConversionResult doUnitConversion(String currentUnit, String requestedUnit, float inputValue ) {
		
		
		Optional<Conversion> conversion = conversions
				.stream()
				.filter((c) -> {
					return c.getCurrentUnit() == currentUnit
							&& c.getRequestedUnit() == requestedUnit;
					})
				.findAny();

		
		return null;
	}
}
	
	
