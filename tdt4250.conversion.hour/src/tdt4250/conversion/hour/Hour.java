package tdt4250.conversion.hour;

import org.osgi.service.component.annotations.*;

import tdt4250.conversion.api.Conversion;
import tdt4250.conversion.util.Unit;

@Component(
		property = {
				Unit.NAME + "=h",
				Unit.PATH + "=tdt4250.conversion.hour#/tdt4250/conversion/hour/hourconversion.txt"}
		)

public class Hour extends Unit implements Conversion{

}
