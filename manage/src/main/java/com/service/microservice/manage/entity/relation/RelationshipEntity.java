package com.service.microservice.manage.entity.relation;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "relationship")
@AllArgsConstructor
@NoArgsConstructor
public class RelationshipEntity {
    @Id
    private Long id;
    private String name;
    private Long parentId;

    @Transient
    private List<RelationshipEntity> children = new ArrayList<>();
}
