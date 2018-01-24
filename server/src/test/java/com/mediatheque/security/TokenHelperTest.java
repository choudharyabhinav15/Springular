package com.mediatheque.security;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.joda.time.DateTimeUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * Created by Ghiles FEGHOUL on 28/12/2017
 */
public class TokenHelperTest {

    private TokenHelper tokenHelper;

    @Before
    public void init() {
        tokenHelper = new TokenHelper();
        DateTimeUtils.setCurrentMillisFixed(20L);
        ReflectionTestUtils.setField(tokenHelper, "EXPIRES_IN", 1);
        ReflectionTestUtils.setField(tokenHelper, "SECRET", "mySecret");
    }

    @Test(expected=ExpiredJwtException.class)
    public void testGenerateTokenExpired() {
        String token = tokenHelper.generateToken("ghiles");
        Jwts.parser()
            .setSigningKey("mySecret")
            .parseClaimsJws(token)
            .getBody();
    }
}
