//package epam.rd.traydakalo.configuration.security.jwt;
//
//
//import org.springframework.beans.factory.annotation.Value;
//
//@Component
//public class JwtTokenProvider {
//
//    private static final String AUTHORIZATION = "Authorization";
//    private static final String BEARER_ = "Bearer ";
//    private static final String EXPIRED_OR_INVALID_JWT_TOKEN = "Expired or invalid JWT token";
//    private static final String ROLES = "roles";
//    private static final String USER_SERVICE_IMPL = "userServiceImpl";
//    private static final String EMPTY_STRING = "";
//
//
//    @Value("${jwt.token.secret-key}")
//    private String secretKey;
//
//    @Value("${jwt.token.expire-length}") // 1h
//    private long validityInMilliseconds;
//
//    private UserDetailsService userDetailsService;
//
//    @PostConstruct
//    protected void init() {
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//    }
//
//    public String createToken(String userName, Set<Role> roles) {
//        Claims claims = Jwts.claims().setSubject(userName);
//        claims.put(ROLES, roles);
//
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(validity)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
//    public String getUsername(String token) {
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
//    }
//
//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }
//
//    public String resolveToken(HttpServletRequest req) {
//        String bearerToken = req.getHeader(AUTHORIZATION);
//        if (bearerToken != null && bearerToken.startsWith(BEARER_)) {
//            return bearerToken.substring(7);
//        }
//        return EMPTY_STRING;
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            if (claims.getBody().getExpiration().before(new Date())) {
//                return false;
//            }
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            throw new InvalidJwtAuthenticationException(EXPIRED_OR_INVALID_JWT_TOKEN);
//        }
//    }
//}
