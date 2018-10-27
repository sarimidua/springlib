package training.com.first.security;

import static training.com.first.security.SecurityConstants.SECRET;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import training.com.first.model.UserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTToken implements Serializable{

	private static final long serialVersionUID = -5613799827865340005L;
	
	public String getUsernameFromToken(String token) 
			throws ExpiredJwtException, UnsupportedJwtException, 
			MalformedJwtException, SignatureException, 
			IllegalArgumentException, UnsupportedEncodingException {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public String getIdFromToken(String token) 
			throws ExpiredJwtException, UnsupportedJwtException, 
			MalformedJwtException, SignatureException, 
			IllegalArgumentException, UnsupportedEncodingException {
		Claims claims = getAllClaimsFromToken(token);
		return String.valueOf(claims.get("id"));
	}
	
	public String getOrgFromToken(String token) 
			throws ExpiredJwtException, UnsupportedJwtException, 
			MalformedJwtException, SignatureException, 
			IllegalArgumentException, UnsupportedEncodingException {
		Claims claims = getAllClaimsFromToken(token);
		return String.valueOf(claims.get("org"));
	}
	
	public String getIdOrgFromToken(String token) 
			throws 
			ExpiredJwtException, UnsupportedJwtException, 
			MalformedJwtException, SignatureException, 
			IllegalArgumentException, UnsupportedEncodingException {
		Claims claims = getAllClaimsFromToken(token);
		return String.valueOf(claims.get("idOrg"));
	}
	
	public Date getExpirationDateFromToken(String token) 
			throws ExpiredJwtException, UnsupportedJwtException, 
			MalformedJwtException, SignatureException, 
			IllegalArgumentException, UnsupportedEncodingException {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		return Jwts.parser()
				.setSigningKey(SECRET.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
	
	private Boolean isTokenExpired(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

	public String generateToken(UserModel user, Authentication auth) {
		return doGenerateToken(user.getId(),
				user.getUsername(), 
				user.getEmail(),
				user.getName(),
				auth);
	}
	
	private String doGenerateToken(Integer id, String username, String email, String name,
			Authentication auth) {
		User user = (User) auth.getPrincipal();
		
		Claims claims = Jwts.claims().setSubject(username);
		claims.put("roles", user.getAuthorities()
				.stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(", ")));
		claims.put("id", id);
		claims.put("email", email);
		claims.put("name", name);
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
				.compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		final String username = getUsernameFromToken(token);
		return (
				username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}