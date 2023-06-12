package com.project.backend.API;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TokenManager {
    private static final Map<String, String> tokenMap = new HashMap<>();
    private static final int TOKEN_LENGTH = 16;

    public static String generateToken(String username) {
        byte[] bytes = new byte[TOKEN_LENGTH];
        new Random().nextBytes(bytes);
        String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        tokenMap.put(token, username);
        return token;
    }

    public static boolean verifyToken(String token) {
        return tokenMap.containsKey(token);
    }

    public static String getUsernameFromToken(String token) {
        return tokenMap.get(token);
    }

    public static boolean removeToken(String token,String username){
        return tokenMap.remove(token,username);
    }
}
