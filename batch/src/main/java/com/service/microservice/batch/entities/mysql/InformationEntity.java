package com.service.microservice.batch.entities.mysql;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "information")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String email;
    private Integer age;
    private String address;
}
