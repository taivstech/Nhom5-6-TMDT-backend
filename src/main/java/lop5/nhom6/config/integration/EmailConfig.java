package lop5.nhom6.config.integration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class EmailConfig {

    @Value("${app.mail.from:noreply@ecommerce.com}")
    private String from;

    @Value("${app.mail.from-name:Ecommerce Support}")
    private String fromName;

    @Value("${app.email.verification.expiration-hours:24}")
    private int verificationExpirationHours;

    @Value("${app.email.verification.base-url:http://localhost:5173}")
    private String baseUrl;

    @Value("${app.email.password-reset.expiration-minutes:30}")
    private int passwordResetExpirationMinutes;
}
