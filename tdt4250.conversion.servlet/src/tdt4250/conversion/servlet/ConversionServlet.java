package tdt4250.conversion.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import tdt4250.conversion.api.ConversionUnit;
import tdt4250.conversion.api.Converter;
import tdt4250.conversion.api.UnitConversionResult;
import javax.servlet.*;
import javax.servlet.http.*;

import org.osgi.service.component.annotations.*;
import org.osgi.service.http.whiteboard.propertytypes.HttpWhiteboardServletPattern;

@Component
@HttpWhiteboardServletPattern("/conversion/*")
public class ConversionServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1L;
	private Converter unitC = new Converter();
	
	@Reference( 
			cardinality = ReferenceCardinality.MULTIPLE,
			policy = ReferencePolicy.DYNAMIC,
			bind = "addConversionUnit",
			unbind = "removeConversionUnit") 
	
	public void addConversionUnit(ConversionUnit cu) {
		unitC.addConversionUnit(cu);
	}
	public void removeConversionUnit(ConversionUnit cu) {
		unitC.removeConversionUnit(cu);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UnitConversionResult result = null;
		
		String current = request.getParameter("current");
		String requested = request.getParameter("requested");
		float inputValue = new Float(request.getParameter("inputValue"));
	
		result = unitC.convert(current.toLowerCase(), requested.toLowerCase(), Float.valueOf(inputValue));
		
		if(result != null) {
		response.setContentType("text/plain");
		PrintWriter writer = response.getWriter();
		writer.print(result.getMessage());
		} 
			
	}	
}