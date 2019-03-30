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

@WebServlet("/")
public class ServletExample extends HttpServlet {

    private JsonReader jsonReader = new JsonReader();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = jsonReader.createUserList();
        for(User u: userList) {
            resp.getWriter().write(u.toString());
            resp.getWriter().write("\n");
        }
    }
}