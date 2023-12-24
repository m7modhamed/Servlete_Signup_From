package com.example.student_signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getStudent")
public class getStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        StringBuilder builder = new StringBuilder();
        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"View.css\">");
        out.println("</head>");

        out.println("<body>");


        out.println("<h1>Student Table</h1>");
        out.println("<table border='1' width='100%' class=\"styled-table\">");

        out.println("<tr> <th>ID</th> <th>First Name</th> <th>Last Name</th> <th>Mid Name</th>" +
                " <th>Email</th> <th>Gender</th> <th>Password</th> <th>Address</th> <th>City</th> <th>Phone</th>" +
                "  <th>Courses</th> <th>Delete</th> </tr>");

        ArrayList<StudentInfo> list = StudentDB.getStudentById((req.getParameter("stdId")));

        for (StudentInfo s : list) {
            //builder.delete(0,builder.length()-1);
            builder.setLength(0);
            /*for (String str : StudentDB.getCourses(s.getId())) {
                builder.append(str).append(" & ");
            }*/
            List<String> coursesList = StudentDB.getCourses(s.getId());
            for (int i = 0; i < coursesList.size(); i++) {
                if (i != 0) {
                    builder.append(" & ");
                }
                builder.append(coursesList.get(i));

            }
            out.printf("<tr>  <td>%d</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td>" +
                            " <td>%s</td> <td>%s</td> <td>%s</td>  <td>%s</td>  <td>%s</td>" +
                            "<td><a href='DeleteStudent?id=%d' class=\"delete\">delete</a></td> </tr>%n"
                    , s.getId(), s.getFirstName(), s.getLastName(), s.getMotherName(), s.getEmail(), s.getGender(), s.getPassword(),
                    s.getAddress(), s.getCity(), s.getPhone(), builder, s.getId());

        }

        //StudentInfo std ;




      /*
      out.println(std.getId()+"/n"+std.getFirstName()+"/n"+std.getLastName()+"/n"+std.getMotherName()+"/n"+std.getEmail()+"/n"
       +std.getAddress()+"/n"+std.getPassword()+"/n"+std.getCity()+"/n"+std.getGender()+"/n"+std.getPhone());
       */




        out.println("</table>");

        out.println( "<button type=\"button\" onclick=\"window.location.href='getStudent.html';\"  class=\"submit\">Get Student (ID)</button>");

        out.println("<div class=\"button\">\n" +
                "        <button type=\"button\" onclick=\"window.location.href='SignUp.html';\"  class=\"btnadd\">Add Student</button>\n" +
                "</div>");

        out.println("</body>");
       // resp.sendRedirect("ViewStudent");

       /* if (state > 0) {
            out.println("<h2>inserted done</h2>");
        } else {
            out.println("<h2>Inserted fail</h2>");
        }*/

    }
}
