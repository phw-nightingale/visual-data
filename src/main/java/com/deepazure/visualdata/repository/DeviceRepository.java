package com.deepazure.visualdata.repository;

import com.deepazure.visualdata.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
