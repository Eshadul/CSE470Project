package com.programming.techie.Blogers.Point.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.security.Key;

@Service
public class JwtProvider {
    private static Key key;
    @PostConstruct
    public void init(){
        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
    public static String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(key)
                .compact();

    }

    public boolean validateToken(String jwt)  throws UnsupportedEncodingException {
        try{
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt);
        return true;
    }
        catch (SignatureException e) {
           System.out.print("signature exception"+e);
        } catch (MalformedJwtException e) {
            System.out.print("token malformed"+e);

        } catch (ExpiredJwtException e) {
            System.out.print("token expired"+e);

        } catch (UnsupportedJwtException e) {
            System.out.print("unsupported"+e);

        } catch (IllegalArgumentException e) {
            System.out.print("Illegal"+e);

        }

        return false;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (Exception e){
            System.out.println(" "+e);
        }
        return claims.getSubject();
    }
}
