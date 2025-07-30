
package com.dipComputer.Dip.Computer.controller;

import com.dipComputer.Dip.Computer.model.CarouselImage;
import com.dipComputer.Dip.Computer.service.CarouselImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")

public class CarouselImageControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CarouselImageService carouselImageService;

    @InjectMocks
    private CarouselImageController carouselImageController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carouselImageController).build();
    }

    @Test
    public void testGetAllCarouselImages() throws Exception {
        CarouselImage image1 = new CarouselImage();
        image1.setId(1L);
        image1.setImageUrl("url1");
        image1.setAltText("alt1");

        CarouselImage image2 = new CarouselImage();
        image2.setId(2L);
        image2.setImageUrl("url2");
        image2.setAltText("alt2");

        List<CarouselImage> imageList = Arrays.asList(image1, image2);

        when(carouselImageService.getAllCarouselImages()).thenReturn(imageList);

        mockMvc.perform(get("/api/carousel-images"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].altText").value("alt1"))
                .andExpect(jsonPath("$[1].altText").value("alt2"));
    }
}
