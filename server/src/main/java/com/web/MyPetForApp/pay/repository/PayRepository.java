package com.web.MyPetForApp.pay.repository;

import com.web.MyPetForApp.pay.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, Long> {
}
