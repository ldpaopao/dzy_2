//package paopao.demo.Interceptor;
//
//import paopao.demo.security.CurrentHolder;
//import paopao.demo.security.JwtUtils;
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import paopao.demo.security.CurrentHolder;
//
//@Slf4j
//@Component
//public class TokenInterceptor implements HandlerInterceptor {
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        //1.获取请求路径
//        String path = request.getRequestURI();
//
//        //2.判断请求路径是否是登录请求
//        if (path.contains("/login")) {
//            return true;
//        }
//        //3.如果请求路径不是登录请求，获取请求头中的token
//        String token = request.getHeader("token");
//
//        //4.判断token是否为空，如果为空，响应401状态码
//        if (token == null || token.length() == 0) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//        //5.如果token不为空，解析token，获取用户信息
//
//
//        //6.如果解析失败，响应401状态码
//        try {
//            Claims claims = JwtUtils.parseToken(token);
//            Integer empId = Integer.valueOf(claims.get("id").toString());
//            CurrentHolder.setCurrentId(empId);
//            log.info("当前用户id为：" + empId);
//            return true;
//        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//    }
//
//    // 👇 新增此方法，在整个请求完成后才清除 ThreadLocal
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        CurrentHolder.remove(); // ✅ 请求结束后再清理
//    }
//}
//
