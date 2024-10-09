package com.service.microservice.manage.entity.account;

import com.service.microservice.manage.entity.audit.AuditEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name = "account")
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AccountEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "my_sequence", allocationSize = 1)
    private Long id;
    private Long idAuth;
    private String name;
    private String email;
    private int age;
    private Date birthDay;
    private String address;
    private int position;
    private String description;

}
