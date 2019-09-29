package tdt4250.conversion.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;


import org.osgi.service.component.annotations.*;
import tdt4250.conversion.api.Conversion;

@Component(
		configurationPolicy = ConfigurationPolicy.REQUIRE
		)


public class Unit implements Conversion{
	
	public static final String NAME = "name";
	public static final String PATH = "path";
	
	private String name;
	private List<String> conversionFormulas;
	
	
	public @interface UnitConfig {
		String name();
		String path() default "";
	}

	@Activate 
	public void activate(BundleContext bc, UnitConfig config) {
		name = config.name();
		String uUrl = config.path();
		
		if(uUrl.length() >0) {
			URL url = null;
			try {
				url = new URL(uUrl);
			} catch (MalformedURLException exception) {
				int position = uUrl.indexOf('#');
				String bundleIdentification = uUrl.substring(0, position);
				String resourcePath = uUrl.substring(position+1);
				for (Bundle b: bc.getBundles()) {
					if(b.getSymbolicName().equals(bundleIdentification)) {
						url = b.getResource(resourcePath);
					}
				}
			try {
				System.out.println("Loading formulas for conversion from " + url);
				conversionFormulas = read(url.openStream());
			} catch (IOException IOe) {
				System.err.println(IOe);
			}
			}
		}
		

	}
	
	
	@Override
	public String getCurrentUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestedUnit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getValue(float inputValue) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<String> getConversionFormulas(){
		return conversionFormulas;
	}
	
	private List<String> read(InputStream input) throws IOException {
		List<String> formulaList = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			formulaList.add(line);
		}
		return formulaList;
	}


}
