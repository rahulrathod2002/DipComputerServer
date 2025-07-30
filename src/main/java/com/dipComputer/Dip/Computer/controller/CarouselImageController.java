package com.dipComputer.Dip.Computer.controller;

import com.dipComputer.Dip.Computer.model.CarouselImage;
import com.dipComputer.Dip.Computer.service.CarouselImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carousel-images")
@CrossOrigin(origins = "*")
public class CarouselImageController {

    @Autowired
    private CarouselImageService carouselImageService;

    @GetMapping
    public List<CarouselImage> getAllCarouselImages() {
        return carouselImageService.getAllCarouselImages();
    }

    @GetMapping("/{id}")
    public CarouselImage getCarouselImageById(@PathVariable Long id) {
        return carouselImageService.getCarouselImageById(id);
    }

    @PostMapping
    public CarouselImage createCarouselImage(@RequestBody CarouselImage carouselImage) {
        return carouselImageService.saveCarouselImage(carouselImage);
    }

    @PutMapping("/{id}")
    public CarouselImage updateCarouselImage(@PathVariable Long id, @RequestBody CarouselImage carouselImage) {
        carouselImage.setId(id);
        return carouselImageService.saveCarouselImage(carouselImage);
    }

    @DeleteMapping("/{id}")
    public void deleteCarouselImage(@PathVariable Long id) {
        carouselImageService.deleteCarouselImage(id);
    }
}
