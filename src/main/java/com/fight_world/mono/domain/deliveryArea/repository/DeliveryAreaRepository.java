package com.fight_world.mono.domain.deliveryArea.repository;

import com.fight_world.mono.domain.deliveryArea.model.DeliveryArea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAreaRepository extends JpaRepository<DeliveryArea, String> {

    List<DeliveryArea> findAllByStoreIdAndDeletedAtIsNull(String storeId);
}
