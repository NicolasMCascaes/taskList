package com.tasklist.api.controller;

import java.io.IOException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasklist.api.service.auth.UserAuthService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/oauth2")
@CrossOrigin
public class OAuth2Controller {
    private final UserAuthService userAuthService;

    public OAuth2Controller(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @GetMapping("/success")
    public void oAuth2Success(OAuth2AuthenticationToken token, HttpServletResponse response) throws IOException {
        String jwt = userAuthService.oAuth2Login(token);
        response.sendRedirect("http://localhost:4200/oauth2/redirect?token=" + jwt);
    }

    @GetMapping("/login")
    public void oAuth2Login(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/google");
    }
}
