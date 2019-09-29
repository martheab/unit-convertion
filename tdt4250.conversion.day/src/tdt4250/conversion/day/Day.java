package tdt4250.conversion.day;

import org.osgi.service.component.annotations.*;

import tdt4250.conversion.api.ConversionUnit;
import tdt4250.conversion.util.Unit;

@Component(
		property = {
				Unit.NAME + "=m",
				Unit.PATH + "=tdt4250.conversion.minute#/tdt4250/conversion/minute/minuteconversion.txt"}
		)

public class  Day extends Unit implements ConversionUnit{


}
