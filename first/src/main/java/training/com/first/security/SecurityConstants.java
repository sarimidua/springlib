package training.com.first.security;

public class SecurityConstants {
	public static final String SECRET = "NezaketNusantaraGroupIndonesia";
	public static final long EXPIRATION_TIME = 300000; // 5min
	public static final long REVOKE_EXPIRATION_TIME = 1; // 1 mili seconds
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String CLAIM_ROLES = "roles";
}
