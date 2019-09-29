package tdt4250.conversion.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Converter {
	
	private static final String DEFAULT_MESSAGE = "Sorry, unit(s) not included in library.";
	private Collection<ConversionUnit> cUnits = new ArrayList<ConversionUnit>();
	
	public void addConversionUnit(ConversionUnit cUnit) {
		cUnits.add(cUnit);
	}
	
	public void removeConversionUnit(ConversionUnit cUnit) {
		cUnits.remove(cUnit);
	}
	
	public UnitConversionResult convert(String currentUnit, String requestedUnit, float inputValue) {
		UnitConversionResult result = null;
		boolean convertionSuccess = false;
		
		// check if current unit exists in library of units
		Optional<ConversionUnit> cUnit = cUnits
				.stream()
				.filter((cu) -> {
					return cu.getCurrentUnit() == currentUnit;}).findAny();
		// if current unit does not exist, send out default message
		if(!cUnit.isPresent()) {
			result = new UnitConversionResult(-1.0f,convertionSuccess, DEFAULT_MESSAGE);
		}
		
		//create list of all formulas that belongs to the current "from-unit"
		List<String> relevantFormulas = cUnit.get().getConversionFormulas();
		if(relevantFormulas != null) {
			
			//check if requested unit exists in different formulas
			Optional<String> formula = relevantFormulas
					.stream()
					.filter((f) -> {
						return f.substring(0,1).equals(requestedUnit);}).findAny();
			// if requested unit does not exist, send out default message
			if(!formula.isPresent()) {
				result = new UnitConversionResult(-1.0f,convertionSuccess, DEFAULT_MESSAGE);
			} else {
				//if current and requested unit exist: convert value
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("JavaScript");
				String convertionString = formula.toString().substring(2, formula.toString().length()).replaceAll(currentUnit, Float.toString(inputValue));
				convertionSuccess = true;
				try {
					result = new UnitConversionResult(Float.valueOf(engine.eval(convertionString).toString()),convertionSuccess,"Unit convertion succesfull!");
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					result = new UnitConversionResult(-1, false, DEFAULT_MESSAGE);
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					result = new UnitConversionResult(-1, false, DEFAULT_MESSAGE);
				}
			}
		}
		return result;
		
	}

}
