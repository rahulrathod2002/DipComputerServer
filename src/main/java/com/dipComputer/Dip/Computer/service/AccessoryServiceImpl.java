package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.model.Accessory;
import com.dipComputer.Dip.Computer.repository.AccessoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessoryServiceImpl implements AccessoryService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Override
    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }

    @Override
    public Accessory getAccessoryById(Long id) {
        return accessoryRepository.findById(id).orElse(null);
    }

    @Override
    public Accessory saveAccessory(Accessory accessory) {
        updateImageUrl(accessory);
        return accessoryRepository.save(accessory);
    }

    @Override
    public void deleteAccessory(Long id) {
        accessoryRepository.deleteById(id);
    }

    @Override
    public Accessory updateAccessory(Long id, Accessory accessoryDetails) {
        Accessory accessory = getAccessoryById(id);
        if (accessory != null) {
            accessory.setName(accessoryDetails.getName());
            accessory.setDescription(accessoryDetails.getDescription());
            accessory.setImageUrl(accessoryDetails.getImageUrl());
            updateImageUrl(accessory);
            return accessoryRepository.save(accessory);
        }
        return null;
    }

    private void updateImageUrl(Accessory accessory) {
        String imageUrl = accessory.getImageUrl();
        if (imageUrl != null && imageUrl.contains("drive.google.com")) {
            String fileId = extractFileIdFromGoogleDriveUrl(imageUrl);
            if (fileId != null) {
                accessory.setImageUrl("api/accessories/image/" + fileId);
            } else {
                accessory.setImageUrl(imageUrl);
            }
        } else {
            accessory.setImageUrl(imageUrl);
        }
    }

    private String extractFileIdFromGoogleDriveUrl(String url) {
        String fileId = null;
        if (url != null) {
            if (url.contains("uc?export=view&id=")) {
                int startIndex = url.indexOf("id=");
                if (startIndex != -1) {
                    fileId = url.substring(startIndex + 3);
                }
            } else {
                int startIndex = url.indexOf("/d/");
                if (startIndex != -1) {
                    int endIndex = url.indexOf("/", startIndex + 3);
                    if (endIndex != -1) {
                        fileId = url.substring(startIndex + 3, endIndex);
                    } else {
                        fileId = url.substring(startIndex + 3);
                    }
                }
            }
        }
        // clean file id
        if (fileId != null) {
            int lastIndex = fileId.indexOf("?");
            if (lastIndex != -1) {
                fileId = fileId.substring(0, lastIndex);
            }
        }
        return fileId;
    }
}
