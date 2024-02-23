package org.example.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class YourController {
    @GetMapping("/endpoint")
    public ResponseEntity<String> yourMethod() {
        return ResponseEntity.ok("done");
    }
}
}
