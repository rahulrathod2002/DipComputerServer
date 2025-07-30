package com.dipComputer.Dip.Computer.controller;

import com.dipComputer.Dip.Computer.Dto.ProductDto;
import com.dipComputer.Dip.Computer.model.Product;
import com.dipComputer.Dip.Computer.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.dipComputer.Dip.Computer.config.SecurityConfig;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")

@WebMvcTest(ProductController.class)
@Import(SecurityConfig.class)
@EnableWebMvc // Ensure full MVC configuration is loaded
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private WebApplicationContext context;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setProductName("Laptop X1");
        product1.setPrice(1200);
        product1.setProcessor("Intel i7");
        product1.setScreenSizeInInch(15.6);
        product1.setRamInGB(16);
        product1.setOs("Windows 11");
        product1.setStorageCapacityGB(512);
        product1.setWarrantyYears(2);
        product1.setIncludesBatteryWarranty(true);
        product1.setIncludesAdapterWarranty(true);
        product1.setTouchscreen(false);
        product1.setHasWebcam(true);
        product1.setProductCondition("New");
        product1.setImageUrl("http://example.com/laptopx1.jpg");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setProductName("Mouse Pro");
        product2.setPrice(25);
        product2.setProcessor("N/A");
        product2.setScreenSizeInInch(0.0);
        product2.setRamInGB(0);
        product2.setOs("N/A");
        product2.setStorageCapacityGB(0);
        product2.setWarrantyYears(1);
        product2.setIncludesBatteryWarranty(false);
        product2.setIncludesAdapterWarranty(false);
        product2.setTouchscreen(false);
        product2.setHasWebcam(false);
        product2.setProductCondition("Used");
        product2.setImageUrl("http://example.com/mousepro.jpg");

        List<Product> productList = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(get("/api/products"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].productName").value("Laptop X1"))
                .andExpect(jsonPath("$[0].price").value(1200))
                .andExpect(jsonPath("$[0].processor").value("Intel i7"))
                .andExpect(jsonPath("$[0].screenSizeInInch").value(15.6))
                .andExpect(jsonPath("$[0].ramInGB").value(16))
                .andExpect(jsonPath("$[0].os").value("Windows 11"))
                .andExpect(jsonPath("$[0].storageCapacityGB").value(512))
                .andExpect(jsonPath("$[0].warrantyYears").value(2))
                .andExpect(jsonPath("$[0].includesBatteryWarranty").value(true))
                .andExpect(jsonPath("$[0].includesAdapterWarranty").value(true))
                .andExpect(jsonPath("$[0].touchscreen").value(false))
                .andExpect(jsonPath("$[0].hasWebcam").value(true))
                .andExpect(jsonPath("$[0].productCondition").value("New"))
                .andExpect(jsonPath("$[0].imageUrl").value("http://example.com/laptopx1.jpg"))
                .andExpect(jsonPath("$[1].productName").value("Mouse Pro"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateProductWithAuth() throws Exception {
        ProductDto newProductDto = new ProductDto();
        newProductDto.setProductName("New Product");
        newProductDto.setPrice(100);
        newProductDto.setProcessor("AMD Ryzen 5");
        newProductDto.setScreenSizeInInch(14.0);
        newProductDto.setRamInGB(8);
        newProductDto.setOs("Ubuntu");
        newProductDto.setStorageCapacityGB(256);
        newProductDto.setWarrantyYears(1);
        newProductDto.setIncludesBatteryWarranty(true);
        newProductDto.setIncludesAdapterWarranty(false);
        newProductDto.setTouchscreen(true);
        newProductDto.setHasWebcam(true);
        newProductDto.setProductCondition("Refurbished");
        newProductDto.setImageUrl("http://example.com/newproduct.jpg");

        Product newProduct = new Product();
        newProduct.setId(3L);
        newProduct.setProductName("New Product");
        newProduct.setPrice(100);
        newProduct.setProcessor("AMD Ryzen 5");
        newProduct.setScreenSizeInInch(14.0);
        newProduct.setRamInGB(8);
        newProduct.setOs("Ubuntu");
        newProduct.setStorageCapacityGB(256);
        newProduct.setWarrantyYears(1);
        newProduct.setIncludesBatteryWarranty(true);
        newProduct.setIncludesAdapterWarranty(false);
        newProduct.setTouchscreen(true);
        newProduct.setHasWebcam(true);
        newProduct.setProductCondition("Refurbished");
        newProduct.setImageUrl("http://example.com/newproduct.jpg");

        when(productService.saveProduct(any(ProductDto.class))).thenReturn(newProduct);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProductDto))
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("New Product"))
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.processor").value("AMD Ryzen 5"))
                .andExpect(jsonPath("$.screenSizeInInch").value(14.0))
                .andExpect(jsonPath("$.ramInGB").value(8))
                .andExpect(jsonPath("$.os").value("Ubuntu"))
                .andExpect(jsonPath("$.storageCapacityGB").value(256))
                .andExpect(jsonPath("$.warrantyYears").value(1))
                .andExpect(jsonPath("$.includesBatteryWarranty").value(true))
                .andExpect(jsonPath("$.includesAdapterWarranty").value(false))
                .andExpect(jsonPath("$.touchscreen").value(true))
                .andExpect(jsonPath("$.hasWebcam").value(true))
                .andExpect(jsonPath("$.productCondition").value("Refurbished"))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/newproduct.jpg"));
    }
}