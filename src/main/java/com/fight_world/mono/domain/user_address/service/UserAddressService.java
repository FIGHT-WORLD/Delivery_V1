package com.fight_world.mono.domain.user_address.service;

import com.fight_world.mono.domain.user_address.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAddressService {

    private final UserAddressRepository userAddressRepository;
}
