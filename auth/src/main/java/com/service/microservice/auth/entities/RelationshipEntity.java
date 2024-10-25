package com.service.microservice.auth.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Table(name = "relationship")
@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
public class RelationshipEntity extends AuditEntity {
    @Id
    private Long id;
    private String name;
    private String parentId;
}
