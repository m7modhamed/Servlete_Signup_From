package com.example.student_signup;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/SaveStudent")
public class SaveStudent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        StudentInfo std = new StudentInfo();
        String firstName="", lastName="", motherName="", email="",
                gender="", password="", address="", city="", phone="", course="";

        firstName = req.getParameter("firstName");
        lastName = req.getParameter("lastName");
        motherName = req.getParameter("motherName");
        email = req.getParameter("Email");
        gender = req.getParameter("gender");
        password = req.getParameter("password");
        address = req.getParameter("address");
        city = switch (Integer.parseInt(req.getParameter("city"))) {
            case 1 -> "Jordan";
            case 2 -> "USA";
            case 3 -> "palestine";
            case 4 -> "Egypt";
            case 5 -> "syria";
            default -> "";
        };
        phone = req.getParameter("phone");

        for (int i = 1; i <= 3; i++) {
            course = req.getParameter("course"+i);
            if (course != null && !course.isEmpty() ) {
                std.addCourse(course);
            }
        }


        std.setFirstName(firstName);
        std.setLastName(lastName);
        std.setMotherName(motherName);
        std.setEmail(email);
        std.setGender(gender);
        std.setPassword(password);
        std.setAddress(address);
        std.setCity(city);
        std.setPhone(phone);


        int state = StudentDB.save(std);

        resp.sendRedirect("ViewStudent");

       /* if (state > 0) {
            out.println("<h2>inserted done</h2>");
        } else {
            out.println("<h2>Inserted fail</h2>");
        }*/

    }
}
