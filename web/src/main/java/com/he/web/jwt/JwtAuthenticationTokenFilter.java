package com.he.web.jwt;

import com.he.common.util.JwtTokenUtil;
import com.he.web.security.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private UserDetailsService userSecurityService;

    //@Autowired
    //private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            //final修饰后只读
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            String username = JwtTokenUtil.getAccountFromToken(authToken);

            //事实上如果我们足够相信token中的数据，也就是我们足够相信签名token的secret的机制足够好，
            //这种情况下，我们可以不用再查询数据库，而直接采用token中的数据。
            //本例中，我们还是通过Spring Security的 @UserDetailsService 进行了数据查询，
            //但简单验证的话，你可以采用直接验证token是否合法来避免昂贵的数据查询。
            logger.info("checking authentication " + username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //数据库再验证可以控制token泄露的情况下，将token失效(修改密码，或特定字段控制)
                UserDetails userDetails = this.userSecurityService.loadUserByUsername(username);
                if (JwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                            request));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
