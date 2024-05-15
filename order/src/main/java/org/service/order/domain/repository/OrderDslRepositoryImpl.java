package org.service.order.domain.repository;

import com.data.BaseQueryDsl;
import com.data.entity.QOrderEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class OrderDslRepositoryImpl extends BaseQueryDsl implements OrderDslRepository {

    public static final QOrderEntity order = QOrderEntity.orderEntity;

    public OrderDslRepositoryImpl() {
        super(QOrderEntity.class);
    }
}
