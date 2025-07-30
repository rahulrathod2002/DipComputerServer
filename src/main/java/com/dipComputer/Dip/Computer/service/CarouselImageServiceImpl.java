package com.dipComputer.Dip.Computer.service;

import com.dipComputer.Dip.Computer.model.CarouselImage;
import com.dipComputer.Dip.Computer.repository.CarouselImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselImageServiceImpl implements CarouselImageService {

    @Autowired
    private CarouselImageRepository carouselImageRepository;

    @Override
    public List<CarouselImage> getAllCarouselImages() {
        return carouselImageRepository.findAll();
    }

    @Override
    public CarouselImage getCarouselImageById(Long id) {
        return carouselImageRepository.findById(id).orElse(null);
    }

    @Override
    public CarouselImage saveCarouselImage(CarouselImage carouselImage) {
        return carouselImageRepository.save(carouselImage);
    }

    @Override
    public void deleteCarouselImage(Long id) {
        carouselImageRepository.deleteById(id);
    }
}
