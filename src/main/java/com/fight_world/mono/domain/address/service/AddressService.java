package com.fight_world.mono.domain.address.service;

import com.fight_world.mono.domain.address.model.AddressDongeupmyun;

public interface AddressService {

    AddressDongeupmyun findByDongeupmyunCode(String code);
}
