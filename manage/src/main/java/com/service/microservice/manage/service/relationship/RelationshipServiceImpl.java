package com.service.microservice.manage.service.relationship;

import com.service.microservice.manage.entity.relation.RelationshipEntity;
import com.service.microservice.manage.repository.relationship.RelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RelationshipServiceImpl implements RelationshipService {

    private final RelationshipRepository relationshipRepository;

    @Override
    public RelationshipEntity findAll() {

        List<RelationshipEntity> children =  relationshipRepository.findAll();

        Map<Long, List<RelationshipEntity>> groupedEntities = new HashMap<>();
        for (RelationshipEntity entity : children) {
            groupedEntities
                    .computeIfAbsent(entity.getParentId(), k -> new ArrayList<>())
                    .add(entity);
        }
        RelationshipEntity root = new RelationshipEntity();
        this.buildHierarchyFromGroups(groupedEntities, root, 0L);

        return root;

    }

    private void buildHierarchyFromGroups(Map<Long, List<RelationshipEntity>> groupedEntities,
                                          RelationshipEntity parent, Long parentId) {
        // Lấy danh sách các thực thể con của parentId
        List<RelationshipEntity> children = groupedEntities.get(parentId);
        if (children != null) {
            for (RelationshipEntity child : children) {
                // Thêm vào danh sách con của parent
                parent.getChildren().add(child);
                // Gọi lại để tìm các con của child
                buildHierarchyFromGroups(groupedEntities, child, child.getId());
            }
        }
    }


}
