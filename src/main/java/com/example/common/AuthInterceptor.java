package com.example.common;

import com.example.entity.Permission;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String servletPath = request.getServletPath();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("/page/end/login.html");
            return false;
        }
        List<Permission> permissions = userService.getPermissions(user.getId());
        for (Permission permission : permissions) {
            if (servletPath.contains(permission.getPath())) {//有路径地址，说明有权限
                return true;
            }
        }response.sendRedirect("/page/end/auth.html");
        return false;
      /*  if (permissions.stream().anyMatch(p -> servletPath.contains(p.getPath())) && !servletPath.contains("index") ) {

        }*/
    }

}
