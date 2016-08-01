package controllers;

import database.MainDbClass;
import page.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by araragi on 7/31/16.
 */
public class MyFirstServlet  extends HttpServlet{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException{


        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("theAnswer", "empty message");

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);


    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String firstDrug = request.getParameter("first_drug");
        String secondDrug = request.getParameter("second_drug");

        response.setContentType("text/html;charset=utf-8");

    /*    if (firstDrug == null || firstDrug.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
     */

        String answer = MainDbClass.selectByDrug(firstDrug) + MainDbClass.selectByDrug(secondDrug);



        pageVariables.put("theAnswer", answer);

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
    }



    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();

        pageVariables.put("parameters", request.getParameterMap().toString());

        return pageVariables;
    }

}
