package lop5.nhom6.services.auth;

import com.nimbusds.jose.JOSEException;
import lop5.nhom6.dto.request.auth.AuthenticationRequest;
import lop5.nhom6.dto.request.auth.ExchangeTokenRequest;
import lop5.nhom6.dto.request.auth.ForgotPasswordRequest;
import lop5.nhom6.dto.request.auth.IntrospectRequest;
import lop5.nhom6.dto.request.auth.ResetPasswordRequest;
import lop5.nhom6.dto.request.user.UserCreationRequest;
import lop5.nhom6.dto.response.auth.AuthenticationTokens;
import lop5.nhom6.dto.response.auth.IntrospectResponse;
import lop5.nhom6.dto.response.auth.LogoutResponse;
import lop5.nhom6.dto.response.auth.OutboundOAuthStateResponse;
import lop5.nhom6.dto.response.user.UserResponse;
import lop5.nhom6.models.user.User;
import jakarta.servlet.http.HttpServletRequest;

import java.text.ParseException;
import java.util.Map;

public interface AuthenticationService {

    IntrospectResponse introspect(IntrospectRequest introspectRequest) throws ParseException, JOSEException;

    UserResponse createUser(UserCreationRequest request);

    AuthenticationTokens authenticate(AuthenticationRequest request);

    LogoutResponse logout(String accessToken, String refreshToken) throws ParseException, JOSEException;

    AuthenticationTokens refreshToken(String refreshToken, String accessToken) throws ParseException, JOSEException;

    void forgotPassword(ForgotPasswordRequest request);

    void resetPassword(ResetPasswordRequest request);

    OutboundOAuthStateResponse issueOutboundOAuthState();

    Map<String, String> generateTokensForOAuth2(User user, HttpServletRequest httpRequest);

    AuthenticationTokens authenticateWithOAuth2Code(ExchangeTokenRequest request, HttpServletRequest httpRequest);
}
