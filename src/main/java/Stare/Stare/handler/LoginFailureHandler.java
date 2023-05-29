package Stare.Stare.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
                                        HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
                                throws IOException, ServletException {

        String errorMessage;

        if(e instanceof BadCredentialsException){
            errorMessage = "이메일 혹은 비밀번호가 틀렸습니다.";
        }
        else if (e instanceof UsernameNotFoundException) {
            errorMessage = "가입되지 않은 계정입니다.";
        }
        else{
            errorMessage = "알 수 없는 이유로 로그인이 거부되었습니다.";
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
        setDefaultFailureUrl("/login?error=true&exception=" + errorMessage);
        super.onAuthenticationFailure(request, response, e);
    }

}
