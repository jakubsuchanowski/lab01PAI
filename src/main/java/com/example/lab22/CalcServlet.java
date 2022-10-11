package com.example.lab22;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(name = "CalcServlet", value = "/CalcServlet")
public class CalcServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        /*
        String ileWizyt = "1";
        Cookie [] cookies = request.getCookies();
        if(cookies !=null ){
            for(int i=0;i<cookies.length;i++){
                Cookie cookie = cookies[i];
                if(ileWizyt.equals(cookie.getName())) ileWizyt = cookie.getValue();
            }
        }
        int ile =1;
        try{
            ile = Integer.parseInt(ileWizyt);
        }
        catch(NumberFormatException nfe){}
*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {

        List<String> history = new ArrayList<>();
        HttpSession session = request.getSession(true);

        String ileWizyt = "1";
        Cookie [] cookies = request.getCookies();
        if(cookies !=null ){
            for(int i=0;i<cookies.length;i++){
                Cookie cookie = cookies[i];
                if("ileWizyt".equals(cookie.getName())) ileWizyt = cookie.getValue();
            }
        }
        String informacja="";
        int ile =1;
        try{
            ile = Integer.parseInt(ileWizyt);
            if(ile == 1) {informacja = "Witaj po raz pierwszy!";}
            else {informacja= "Witaj po raz kolejny!";}

        }
        catch(NumberFormatException nfe){}

        Cookie c = new Cookie("ileWizyt",String.valueOf(ile+1));
        response.addCookie(c);



        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title></title></head><body><h2>" + informacja +
                        "</h2></body></html>");


        String operation;
        Double parametr1=null;
        Double parametr2=null;
        String odpowiedz="";
        try {
            parametr1 = Double.valueOf(request.getParameter("parametr1"));
        } catch (NumberFormatException | NullPointerException e) {
            odpowiedz = odpowiedz + "W 1 polu musisz podać liczbę!";
        }
        try {
            parametr2 = Double.valueOf(request.getParameter("parametr2"));
        } catch (NumberFormatException | NullPointerException e) {
            odpowiedz = odpowiedz + "W 2 polu musisz podać liczbę!";
        }
        try {
            operation = request.getParameter("operation");
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        if (odpowiedz.equals("")) {
            if (parametr2 == 0 && Objects.equals(operation, "/")) {
                odpowiedz = String.valueOf(parametr1) + " " + operation + " " + String.valueOf(parametr2) + "= Nie dziel przez zero!";
                wyswietl(odpowiedz, response, request);
            } else {
                odpowiedz = String.valueOf(parametr1)+" "+operation+" "+String.valueOf(parametr2)+" = " + String.valueOf(oblicz(operation, parametr1, parametr2));
                wyswietl(odpowiedz, response, request);
            }
        }
        else {
            wyswietl(odpowiedz,response,request);
        }
    }



    public Double oblicz(String operation, Double parametr1, Double parametr2){
        Double wynik;
        switch (operation){
            case "+":
                wynik= parametr1 + parametr2;
                break;
            case "-":
                wynik = parametr1 - parametr2;
                break;
            case "*":
                wynik = parametr1 * parametr2;
                break;
            case "/":
                wynik = parametr1/parametr2;
                break;
            default: wynik = null;
        }
     return wynik;
    }


    public void wyswietl(String odpowiedz, HttpServletResponse response, HttpServletRequest request) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Wynik obliczeń</title> ");
        out.println("</head>");
        out.println("<body>");

        out.println("<h2>"+odpowiedz+"</h2>");
        out.println("<br><a href = 'calc.html'>Kalkulator</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
