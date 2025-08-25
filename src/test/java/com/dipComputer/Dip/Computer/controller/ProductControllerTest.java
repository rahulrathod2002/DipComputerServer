// package com.dipComputer.Dip.Computer.controller;

// import com.dipComputer.Dip.Computer.Dto.ProductDto;
// import com.dipComputer.Dip.Computer.model.Product;
// import com.dipComputer.Dip.Computer.service.ProductService;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.context.annotation.Import;
// import org.springframework.http.MediaType;
// import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.web.context.WebApplicationContext;
// import com.dipComputer.Dip.Computer.config.SecurityConfig;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// import java.util.Arrays;
// import java.util.List;

// import static org.mockito.Mockito.when;
// import static org.mockito.ArgumentMatchers.any;
// import static
// org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
// import static
// org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import org.springframework.test.context.ActiveProfiles;

// @ActiveProfiles("test")

// @WebMvcTest(ProductController.class)
// @Import(SecurityConfig.class)
// @EnableWebMvc // Ensure full MVC configuration is loaded
// public class ProductControllerTest {

// @Autowired
// private MockMvc mockMvc;

// @MockBean
// private ProductService productService;

// @Autowired
// private WebApplicationContext context;

// private ObjectMapper objectMapper = new ObjectMapper();

// @BeforeEach
// public void setup() {
// mockMvc = MockMvcBuilders
// .webAppContextSetup(context)
// .apply(springSecurity())
// .build();
// }

// @Test
// public void testGetAllProducts() throws Exception {
// Product product1 = new Product();
// product1.setId(1L);
// product1.setProductName("Laptop X1");
// product1.setPrice("1200");
// product1.setProcessor("Intel i7");
// product1.setRam("16");
// product1.setStorage("512");
// product1.setWarranty("2");
// product1.setDescription("A powerful laptop");

// Product product2 = new Product();
// product2.setId(2L);
// product2.setProductName("Mouse Pro");
// product2.setPrice("25");
// product2.setProcessor("N/A");
// product2.setRam("N/A");
// product2.setStorage("N/A");
// product2.setWarranty("1");
// product2.setDescription("A wireless mouse");

// List<Product> productList = Arrays.asList(product1, product2);

// when(productService.getAllProducts()).thenReturn(productList);

// mockMvc.perform(get("/api/products"))
// .andDo(print())
// .andExpect(status().isOk())
// .andExpect(jsonPath("$.length()").value(2))
// .andExpect(jsonPath("$[0].productName").value("Laptop X1"))
// .andExpect(jsonPath("$[0].price").value("1200"))
// .andExpect(jsonPath("$[0].processor").value("Intel i7"))
// .andExpect(jsonPath("$[0].ram").value("16"))
// .andExpect(jsonPath("$[0].storage").value("512"))
// .andExpect(jsonPath("$[0].warranty").value("2"))
// .andExpect(jsonPath("$[0].description").value("A powerful laptop"))
// .andExpect(jsonPath("$[1].productName").value("Mouse Pro"));
// }

// @Test
// @WithMockUser(roles = "ADMIN")
// public void testCreateProductWithAuth() throws Exception {
// ProductDto newProductDto = new ProductDto();
// newProductDto.setProductName("New Product");
// newProductDto.setPrice("100");
// newProductDto.setProcessor("AMD Ryzen 5");
// newProductDto.setRam("8");
// newProductDto.setStorage("256");
// newProductDto.setWarranty("1");
// newProductDto.setDescription("A new product");

// Product newProduct = new Product();
// newProduct.setId(3L);
// newProduct.setProductName("New Product");
// newProduct.setPrice("100");
// newProduct.setProcessor("AMD Ryzen 5");
// newProduct.setRam("8");
// newProduct.setStorage("256");
// newProduct.setWarranty("1");
// newProduct.setDescription("A new product");

// when(productService.saveProduct(any(ProductDto.class))).thenReturn(newProduct);

// mockMvc.perform(post("/api/products")
// .contentType(MediaType.APPLICATION_JSON)
// .content(objectMapper.writeValueAsString(newProductDto))
// .with(csrf()))
// .andExpect(status().isOk())
// .andExpect(jsonPath("$.productName").value("New Product"))
// .andExpect(jsonPath("$.price").value("100"))
// .andExpect(jsonPath("$.processor").value("AMD Ryzen 5"))
// .andExpect(jsonPath("$.ram").value("8"))
// .andExpect(jsonPath("$.storage").value("256"))
// .andExpect(jsonPath("$.warranty").value("1"))
// .andExpect(jsonPath("$.description").value("A new product"));
// }
// }