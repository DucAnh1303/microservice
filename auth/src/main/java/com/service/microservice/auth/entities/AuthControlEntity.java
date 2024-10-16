package com.service.microservice.auth.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "auth_control")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AuthControlEntity extends AuditEntity {
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String token;
    @Column
    private String refreshToken;
}
