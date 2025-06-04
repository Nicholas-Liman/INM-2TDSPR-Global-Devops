package com.example.globaljava;

import com.example.globaljava.model.*;
import com.example.globaljava.repositories.*;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

@SpringBootApplication
@EnableAdminServer
public class GlobalJavaApplication {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("pt", "BR"));
        SpringApplication.run(GlobalJavaApplication.class, args);
    }
}
