package sec04.ex02;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetServletContext extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext context;
	/*
	 * 1. 서블릿 생성자에서는 getSetvletConfig()메서드를 호출할수 없다. 
	 * init()메서드가 호출되고 난 후에야 서블릿은 정체성을 가지기 때문이다.
	 * 
	 * GenericSetvlet에는 init(ServletConfig)와 init()메소드가 있다. 
	 * init(ServletConfig) 메소드는 내부적으로 init()를 호출한다. 
	 * 이렇게 하면 개발자가 init()메소드를 재정의 할수 있다. 
	 * init(ServletConfig)를 재정의 말란법은 없다. 
	 * 재정의 하려면 super.init(ServletConfig); 을 먼저 기입하자.
	 * 백번 양보해도 개발자가 init(ServletConfig)를 재정의 할필요는 없다. 
	 * getServletConfig()메서드로 ServletConfig를 리턴받으면 되기 때문이다.
	 */
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		context = config.getServletContext();
//	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/cget");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// getServletContext()
		context = getServletContext();
		
		// getAttribute(String name)
		out.print("<h1>getAttribute(member)</h1>");
		List member = (ArrayList) context.getAttribute("member");
		String name = (String) member.get(0);
		int age = (Integer) member.get(1);
		out.print("<html><body>");
		out.print(name +"<br>");
		out.print(age + "<br>");
		out.print("<hr>");

		// getAttributeNames()
		out.print("<h1>getAttributeNames()</h1>");
		Enumeration<String> names = context.getAttributeNames();
		while(names.hasMoreElements()){
			out.print(names.nextElement()+"<br>");
		}
		out.print("<hr>");

		// getContext(String uripath)
		out.print("<h1>getContext(/tewt)</h1>");
		ServletContext uricontext = context.getContext("/tewt/cget");
		System.out.println(uricontext.getContextPath());
		member = (ArrayList)uricontext.getAttribute("member");
		name = (String) member.get(0);
		age = (Integer) member.get(1);
		out.print(name +"<br>");
		out.print(age + "<br>");
		out.print("<hr>");

		// getInitParmeter(String name)
		out.print("<h1>getInitParmeter(mm)</h1>");
		Enumeration<String> enums = context.getInitParameterNames();
		while(enums.hasMoreElements()){
			String check = (String)enums.nextElement();
			String s = context.getInitParameter(check);
			out.print(s+"<br>");
		}
		out.print("<h2>getAttribute(mm)</h2>");
		out.print((String)context.getAttribute("mm")+ "<br>");
		out.print("<h2>getInitParameter(mm)</h2>");
		out.print((String)context.getInitParameter("mm")+"<br>");
		out.print("<hr>");

		// getMajorVersion()
		out.print("<h1>getMajorVersion()</h1>");
		int major = context.getMajorVersion();
		out.print(major +"<br>");
		out.print("<hr>");

		// getRealPath(String path)
		out.print("<h1>getRealPath(/cset)</h1>");
		String realpath = context.getRealPath("/cset");
		out.print(realpath +"<br>");
		out.print("<hr>");

		// getResource(String path)
		out.print("<h1>getResource(popUp.html)</h1>");
		URL url = context.getResource("popUp.html");
		URLConnection urlCon = url.openConnection();
		urlCon.connect();
		InputStream instream = urlCon.getInputStream();
		try {
			int c;
			while((c = instream.read()) != -1 ) {
				out.print((char)c);
			}
			instream.close();
		}catch(IOException e) {

		}
		out.print(urlCon.getContentLengthLong()+"<br>");


		// getServerInfo()
		out.print("<h1>getServerInfo()</h1>");
		String ServerInfo = context. getServerInfo();
		out.print(ServerInfo +"<br>");
		out.print("<hr>");

		// getServletContextName()
		out.print("<h1>getServletContextName()</h1>");
		String getServletContextName = context. getServletContextName();
		out.print(getServletContextName +"<br>");
		out.print("<hr>");

		// log(String msg)
		out.print("<h1>log(good)</h1>");
		context.log("good");
		out.print("<hr>");

		// removeAttribute(String name)
		out.print("<h1>removeAttribute(member)</h1>");
		context.removeAttribute("member");
		out.print("<hr>");

		// setAttribute(String name, Object object)
		out.print("<h1>setAttribyte(String name, Object object)</h1>");
		String members = "gg";
		context.setAttribute("member", members);
		out.print((String)context.getAttribute("member")+"<br>");
		out.print("<hr>");

		// config -> getInitParameter
		out.print("<h1>config -> getInitParameter(mmm)</h1>");
		
			Enumeration e = getServletConfig().getInitParameterNames();
			while(e.hasMoreElements()){
				String check = (String)e.nextElement();
				String s = getServletConfig().getInitParameter(check);
				out.print(s+"<br>");
			}
		out.print("<hr>");
		
		// member contain???
		out.print("<h1>config -> getInitParameter(member)</h1>");
//		out.print((String)getServletConfig().getServletContext().getAttribute("member"));
		out.print((String)getServletConfig().getInitParameter("member"));
		out.print("<hr>");
		

		out.print("</body></html>");
	} 
} 
