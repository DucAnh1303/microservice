package com.service.microservice.manage.web;

import com.service.microservice.manage.service.relationship.RelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RelationShipController {

    private final RelationshipService relationshipService;

    @GetMapping("/manage/relationship")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(relationshipService.findAll(), HttpStatus.OK);
    }
}
