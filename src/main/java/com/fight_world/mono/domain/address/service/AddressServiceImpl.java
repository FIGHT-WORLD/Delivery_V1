package com.fight_world.mono.domain.address.service;

import static com.fight_world.mono.domain.address.message.ExceptionMessage.ADDRESS_NOT_FOUND;

import com.fight_world.mono.domain.address.exception.AddressException;
import com.fight_world.mono.domain.address.model.AddressDongeupmyun;
import com.fight_world.mono.domain.address.repository.AddressDongeupmyunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressDongeupmyunRepository addressDongeupMyunRepository;

    @Override
    public AddressDongeupmyun findByDongeupmyunCode(String code) {
        return addressDongeupMyunRepository.findByCodeAndDeletedAtIsNull(code)
                .orElseThrow(() -> new AddressException(ADDRESS_NOT_FOUND));
    }
}
