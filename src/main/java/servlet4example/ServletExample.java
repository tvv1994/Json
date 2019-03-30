package servlet4example;

import models.User;
import utils.JsonReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/hello")
public class ServletExample extends HttpServlet {

    private JsonReader jsonReader = new JsonReader();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = jsonReader.createUserList();
        req.setAttribute("users", users);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/showUsers.jsp");
        dispatcher.forward(req, resp);
    }
}