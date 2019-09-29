package tdt4250.conversion.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import tdt4250.conversion.api.Conversion;
import tdt4250.conversion.api.UnitConversion;
import tdt4250.conversion.api.UnitConversionResult;

import javax.servlet.*;
import javax.servlet.http.*;

import org.osgi.service.component.annotations.*;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletPattern;

@Component
@HttpWhiteboardServletPattern("/conversion/*")
public class ConversionServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	private UnitConversion unitC = new UnitConversion();
	
	@Reference( 
			cardinality = ReferenceCardinality.MULTIPLE,
			policy = ReferencePolicy.DYNAMIC,
			bind = "addConversion",
			unbind = "removeConversion") 
	
	public void addConversion(Conversion conv) {
		unitC.addConversion(conv);
	}
	public void removeConversion(Conversion conv) {
		unitC.removeConversion(conv);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String current = request.getParameter("from");
		String requested = request.getParameter("to");
		float inputValue = new Float(request.getParameter("value"));
		UnitConversionResult result = unitC.doUnitConversion(current, requested, inputValue);
		
		PrintWriter writer = response.getWriter();
		writer.print(result.getMessage());
	}
	
	
	
	
}