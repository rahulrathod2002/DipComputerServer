package com.dipComputer.Dip.Computer.controller;

import com.dipComputer.Dip.Computer.model.Accessory;
import com.dipComputer.Dip.Computer.service.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

@RestController
@RequestMapping("/api/accessories")
@CrossOrigin(origins = "*")
public class AccessoryController {

    @Autowired
    private AccessoryService accessoryService;

    @GetMapping
    public List<Accessory> getAllAccessories() {
        return accessoryService.getAllAccessories();
    }

    @GetMapping("/{id}")
    public Accessory getAccessoryById(@PathVariable Long id) {
        return accessoryService.getAccessoryById(id);
    }

    @PostMapping
    public Accessory createAccessory(@RequestBody Accessory accessory) {
        return accessoryService.saveAccessory(accessory);
    }

    @PutMapping("/{id}")
    public Accessory updateAccessory(@PathVariable Long id, @RequestBody Accessory accessory) {
        return accessoryService.updateAccessory(id, accessory);
    }

    @DeleteMapping("/{id}")
    public void deleteAccessory(@PathVariable Long id) {
        accessoryService.deleteAccessory(id);
    }

    @GetMapping("/image/{fileId}")
    public ResponseEntity<byte[]> getImage(@PathVariable String fileId) throws IOException {
        String url = "https://drive.google.com/uc?export=view&id=" + fileId;
        java.net.URL imageUrl = new java.net.URL(url);

        // Open connection to Google Drive link
        URLConnection connection = imageUrl.openConnection();
        String contentType = connection.getContentType(); // auto-detects (image/jpeg, image/png, etc.)

        try (InputStream in = connection.getInputStream()) {
            byte[] imageBytes = in.readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        }
    }
}
