package com.service.microservice.manage.entity.account;

import com.service.microservice.manage.entity.audit.AuditEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;
    private String name;
    private String email;
    private int age;
    private Date birthDay;
    private String address;
    private int position;
    private String description;

}
