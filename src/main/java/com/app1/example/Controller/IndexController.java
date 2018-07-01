package com.app1.example.Controller;

import com.app1.example.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController
{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public IndexController(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping(method=RequestMethod.GET)
    public String index2(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getRequestURI());

        System.out.println("root");

        //model.addAttribute("message", "/index");

        return "index/index";
    }

    @RequestMapping("/index1")
    public String index1(ModelMap model) {
        String sql = "SELECT * FROM user";

        List<User> listContact = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password")
                );
            }
        });

        for (User user : listContact) {
            System.out.println(user.getName());
        }



        model.addAttribute("message", "/index1");

        return "index/index";
    }

    @RequestMapping(value = {"/", "/welcome"})
    public String Welcome() {
        return "index/welcome";
    }
}