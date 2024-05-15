package org.service.shipping.domain.repository;

import com.data.BaseQueryDsl;
import com.data.entity.QShippingEntity;
import com.data.entity.ShippingEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class ShippingDslRepositoryImpl extends BaseQueryDsl implements ShippingDslRepository {

    public final static QShippingEntity shipping = QShippingEntity.shippingEntity;

    public ShippingDslRepositoryImpl() {
        super(ShippingEntity.class);
    }
}
