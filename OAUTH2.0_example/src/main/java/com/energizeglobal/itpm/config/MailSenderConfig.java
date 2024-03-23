package com.energizeglobal.itpm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author Arman Arshakyan
 */
@Configuration
public class MailSenderConfig {
    @Value("${spring.mail.username}")
    private String email;

    @Value("${spring.mail.password}")
    private String password;

    @Value("#{new String('${spring.mail.host}')}")
    private String host;

    @Value("#{new Integer('${spring.mail.port}')}")
    private Integer port;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);

        javaMailSender.setUsername(email);
        javaMailSender.setPassword(password);

        Properties mailSenderProperties = javaMailSender.getJavaMailProperties();
        mailSenderProperties.put("mail.smtp.auth", "true");
        mailSenderProperties.put("mail.transport.protocol", "smtp");
        mailSenderProperties.put("mail.smtp.starttls.enable", "true");
        mailSenderProperties.put("mail.debug", "true");
        int timeOutInMilliseconds = 5000;
        mailSenderProperties.put("mail.smtp.connectiontimeout", timeOutInMilliseconds);
        mailSenderProperties.put("mail.smtp.timeout", timeOutInMilliseconds);
        mailSenderProperties.put("mail.smtp.writetimeout", timeOutInMilliseconds);

        return javaMailSender;
    }
}