package com.example.lab22;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    protected Date date1;
    protected DateFormat dateFormat;

    @Override
    public void init(){
        message = "Hello World!";
        date1 = new Date();
        dateFormat = new SimpleDateFormat("yyy-MM-dd");

    }
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.setContentType("text/html;charset=UTF-8");
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h2>Dane serwera:</h2");
        out.println("<p>request.getServerName(): "+request.getServerName()+"</p>");
        out.println("<p>request.getServerPort(): "+request.getServerPort()+"</p>");
        out.println("<p>request.getRemoteHost(): "+request.getRemoteHost()+"</p>");
        out.println("<p>request.getRemoteAddr(): " + request.getRemoteAddr() + "</p>");

        out.println("<h2>Szczególy żądania: </h2>");
        out.println("<p>request.getMethod(): " + request.getMethod() + " </p>");
        out.println("<p>request.getQueryString(): " + request.getQueryString() +
                "</p>");
        out.println("<h2> Daty:</h2>");
        out.println("<p>Data z metody init(): "+dateFormat.format(date1)+"</p>");
        out.println("<p>Data z processRequest: "+new Date()+"</p>");
        out.println("<br><a href = 'index.jsp'>Strona główna</a>");
        out.println("</body></html>");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request,response);

    }

    public void destroy() {
    }
}