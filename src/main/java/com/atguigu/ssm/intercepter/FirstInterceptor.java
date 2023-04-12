package com.atguigu.ssm.intercepter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class FirstInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String uri = request.getRequestURI();
        //判断当前请求地址是否登录或者是注册地址
        if(uri.contains("Login") || uri.contains("login")|| uri.contains("register"))
        {
            //登录请求，直接放行
            return true;
        }
        else
        {
            //判断用户是否登录
            if(request.getSession().getAttribute("user")!=null)
            {
                //说明已经登录，放行
                return true;
            }
            else
            {
                //没有登录，跳转到登录界面
                response.sendRedirect(request.getContextPath() + "/toLoginPage");
            }
        }
        //默认拦截
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("FirstInterceptor-->postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("FirstInterceptor-->afterCompletion");
    }
}
