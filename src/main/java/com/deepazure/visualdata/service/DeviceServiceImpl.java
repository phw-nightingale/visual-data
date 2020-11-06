package com.deepazure.visualdata.service;

import com.deepazure.visualdata.entity.Device;
import com.deepazure.visualdata.repository.DeviceRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device> implements DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public JpaRepository<Device, Long> getRepository() {
        return deviceRepository;
    }

}
