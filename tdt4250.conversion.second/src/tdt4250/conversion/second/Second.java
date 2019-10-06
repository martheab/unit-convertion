package tdt4250.conversion.second;

import org.osgi.service.component.annotations.*;
import tdt4250.conversion.api.ConversionUnit;
import tdt4250.conversion.util.Unit;

@Component(
		property = {
				Unit.NAME + "=s",
				Unit.PATH + "=tdt4250.conversion.second#/tdt4250/conversion/second/secondconversion.txt"}
		)

public class Second extends Unit implements ConversionUnit{

}
