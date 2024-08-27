package com.fight_world.mono.domain.order.dto.request;

import java.util.List;

public record OrderCreateRequestDto(
    String store_id,
    List<OrderMenuRequestDto> menu_ids,
    String delivery_type
) {

}
//{
//   "store_id":"id",
//           "menu_ids":[
//    {
//        "id":"id",
//            "cnt":1
//    }
//   ],
//           "delivery_type":"DELIVERY"
//}