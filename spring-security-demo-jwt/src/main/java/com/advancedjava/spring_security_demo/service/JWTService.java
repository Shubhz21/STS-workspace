package com.advancedjava.spring_security_demo.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
//	private byte[] secretKey ;
//	
//	public JWTService() {
//		KeyGenerator keyGen;
//		
//		try {
//			keyGen = KeyGenerator.getInstance("HmacSHA256");
//			SecretKey sk = keyGen.generateKey();
//			secretKey = sk.getEncoded();
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
	
	private static final String SECRET_KEY = "my-super-secret-key-that-is-long-enough-123!";
	
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<String, Object>();
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 *60 *10))
				.and()
				.signWith(getKey())
				.compact();
	}
	
	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

		//return Keys.hmacShaKeyFor(secretKey);
	}
	
	public String extractUserName(String token) {
        // extract the username from jwt token
        //return extractClaim(token, Claims::getSubject);
		Claims claim = extractAllClaims(token);
		return claim.getSubject();
    }

//    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        //return extractClaim(token, Claims::getExpiration);
    	Claims claim = extractAllClaims(token);
		return claim.getExpiration(); 
    }
}
