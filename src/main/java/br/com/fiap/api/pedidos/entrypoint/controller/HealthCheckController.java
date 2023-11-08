package br.com.fiap.api.pedidos.entrypoint.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health-check")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<String> getAppHealth() {
        return new ResponseEntity<>("health update to V3 - status: OK", HttpStatus.OK);
    }
}
