package controller;



import net.sf.json.JSONObject;
import org.json.*;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;


import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IUserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/register")
    public void userRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonObject=new JSONObject();
        User user = this.userService.userLogin(request.getParameter("username"));
        User newUser=new User();
        if (user!=null){
            jsonObject.put("status",400);
            jsonObject.put("content","Already exist");
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(jsonObject.toString());
            response.getWriter().close();
        }
        else {
            String userName=request.getParameter("username");
            String password=request.getParameter("password");
            newUser.setPassword(password);
            newUser.setUsername(userName);

            userService.insertUser(newUser);
           jsonObject.put("status",200);
            jsonObject.put("content","success");
            response.getWriter().write(jsonObject.toString());
            response.getWriter().close();
        }
    }


    @RequestMapping("Login")
    public void userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject resultObject=new JSONObject();
        String userName=request.getParameter("username");
        User user=userService.userLogin(userName);
        if (user==null){
            resultObject.put("status",404);
            resultObject.put("content","Not exist");
            response.getWriter().write(resultObject.toString());
        }
        else if (!request.getParameter("password").equals(user.getPassword())){
            resultObject.put("status",400);
            resultObject.put("content","Password incorrect");
            response.getWriter().write(resultObject.toString());
        }
        else {
            resultObject.put("status",200);
            resultObject.put("content","success");
            response.getWriter().write(resultObject.toString());
        }



    }

}
