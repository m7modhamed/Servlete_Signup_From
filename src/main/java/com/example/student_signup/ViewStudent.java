package com.example.student_signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet("/ViewStudent")
public class ViewStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<head>");
        out.println("<link rel=\"stylesheet\" href=\"View.css\">");
        out.println("</head>");

        out.println("<body>");


        out.println("<h1>Student Table</h1>");


        List<StudentInfo> list = null;
        list = StudentDB.getAllStudent();
        StringBuilder builder = new StringBuilder();
        out.println("<table border='1' width='100%' class=\"styled-table\">");

        out.println("<tr> <th>ID</th> <th>First Name</th> <th>Last Name</th> <th>Mother Name</th>" +
                " <th>Email</th> <th>Gender</th> <th>Password</th> <th>Address</th> <th>City</th> <th>Phone</th>" +
                "  <th>Courses</th> <th>Delete</th> </tr>");
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

        out.println("</table>");


        out.println("<div class=\"button\">\n" +
                "        <button type=\"button\" onclick=\"window.location.href='SignUp.html';\"  class=\"btnadd\">Add Student</button>\n" +
                "</div>");
        out.println("</body>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
