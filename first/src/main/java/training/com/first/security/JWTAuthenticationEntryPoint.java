package training.com.first.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationEntryPoint  implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 1706986846567352939L;

	@Override
	public void commence(HttpServletRequest arg0, 
			HttpServletResponse arg1, 
			AuthenticationException arg2)
			throws IOException, ServletException {
		arg1.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZE");
	}

}

