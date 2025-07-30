package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.model.CarouselImage;

import java.util.List;

public interface CarouselImageService {
    List<CarouselImage> getAllCarouselImages();
    CarouselImage getCarouselImageById(Long id);
    CarouselImage saveCarouselImage(CarouselImage carouselImage);
    void deleteCarouselImage(Long id);
}
