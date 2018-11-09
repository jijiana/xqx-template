package com.xqx.oauth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.xqx.base.exception.ErrorCode;
import com.xqx.oauth.exception.TokenException;
import com.xqx.oauth.exception.TokenExpiredException;

import java.util.Date;

public class JWTHelper {

    private static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    private static final String PAYLOAD = "payload";


    /**
     *
     * 加密
     * @param object    数据对象
     * @param expireTime  有效期，单位：秒
     * @param <T>
     * @return
     * @throws TokenException
     */
    public static <T> String sign(T object, Long expireTime) throws TokenException {
        try {
            String jsonString = new Gson().toJson(object, object.getClass());
            return sign(jsonString,expireTime);
        }catch (Exception e){
            throw new TokenException(e, ErrorCode.TOKEN_EXCEPTION,e.getMessage());
        }
    }

    /**
     *
     * 加密
     * @param context    数据对象
     * @param expireTime  有效期，单位：秒
     * @return
     * @throws TokenException
     */
    public static  String sign(String context, Long expireTime) throws TokenException {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create().withClaim(PAYLOAD, context)
                    .withExpiresAt(new Date(System.currentTimeMillis() + expireTime * 1000)).sign(algorithm);
        }catch (Exception e){
            throw new TokenException(e, ErrorCode.TOKEN_EXCEPTION,e.getMessage());
        }
    }

    /**
     * 解密Token
     * @param token     Token字符串
     * @param clazz     解密后数据类型
     * @param <T>
     * @return
     * @throws TokenExpiredException
     * @throws TokenException
     */
    public static<T> T unsign(String token, Class<T> clazz) throws TokenExpiredException, TokenException {
        try {
            final Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return new Gson().fromJson(jwt.getClaims().get(PAYLOAD).asString(), clazz);
        } catch (com.auth0.jwt.exceptions.TokenExpiredException e){
            throw new TokenExpiredException(e,ErrorCode.TOKEN_EXPIRED,e.getMessage());
        } catch (Exception e){
            throw new TokenException(e, ErrorCode.TOKEN_EXCEPTION,e.getMessage());
        }
    }

    /**
     * 解密Token
     * @param token     Token字符串
     * @return
     * @throws TokenExpiredException
     * @throws TokenException
     */
    public static String unsign(String token) throws TokenExpiredException, TokenException {
        try {
            final Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims().get(PAYLOAD).asString();
        } catch (com.auth0.jwt.exceptions.TokenExpiredException e){
            throw new TokenExpiredException(e,ErrorCode.TOKEN_EXPIRED,e.getMessage());
        } catch (Exception e){
            throw new TokenException(e, ErrorCode.TOKEN_EXCEPTION,e.getMessage());
        }
    }

}
