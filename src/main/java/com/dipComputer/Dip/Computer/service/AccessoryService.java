package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.model.Accessory;

import java.util.List;

public interface AccessoryService {
    List<Accessory> getAllAccessories();
    Accessory getAccessoryById(Long id);
    Accessory saveAccessory(Accessory accessory);
    void deleteAccessory(Long id);
    Accessory updateAccessory(Long id, Accessory accessory);
}
