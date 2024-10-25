package com.service.microservice.manage.repository.relationship;

import com.service.microservice.manage.entity.relation.RelationshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationshipRepository extends JpaRepository<RelationshipEntity, Long>, RelationshipDslRepository {

    List<RelationshipEntity> findByParentId(Long parentId);
}
