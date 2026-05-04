package lop5.nhom6.services.product;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import lop5.nhom6.dto.request.product.ProductRequest;
import lop5.nhom6.dto.response.product.ProductResponse;
import lop5.nhom6.exceptions.AppException;
import lop5.nhom6.exceptions.ErrorCode;
import lop5.nhom6.mappers.product.ProductMapper;
import lop5.nhom6.models.product.Category;
import lop5.nhom6.models.product.Product;
import lop5.nhom6.models.product.ProductImage;
import lop5.nhom6.repositories.product.CategoryRepository;
import lop5.nhom6.repositories.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ProductMapper productMapper;

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        if (productRepository.existsBySlug(request.getSlug())) {
            throw new AppException(ErrorCode.ALREADY_EXISTS);
        }

        Product product = productMapper.toProduct(request);

        // Associate category
        if (request.getCategoryId() != null && !request.getCategoryId().isEmpty()) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
            product.setCategory(category);
        }

        // Handle images
        if (request.getImages() != null) {
            for (ProductRequest.ProductImageRequest imgReq : request.getImages()) {
                ProductImage image = productMapper.toProductImage(imgReq);
                product.addImage(image);
            }
        }

        return productMapper.toProductResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    public ProductResponse getProductBySlug(String slug) {
        return productRepository.findBySlug(slug)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    @Transactional
    public ProductResponse updateProduct(String id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (!product.getSlug().equals(request.getSlug()) && productRepository.existsBySlug(request.getSlug())) {
            throw new AppException(ErrorCode.ALREADY_EXISTS);
        }

        productMapper.updateProduct(product, request);

        // Update category
        if (request.getCategoryId() != null && !request.getCategoryId().isEmpty()) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
            product.setCategory(category);
        } else {
            product.setCategory(null);
        }

        // Update images (simple strategy: clear and re-add for this example)
        if (request.getImages() != null) {
            product.getImages().clear();
            for (ProductRequest.ProductImageRequest imgReq : request.getImages()) {
                ProductImage image = productMapper.toProductImage(imgReq);
                product.addImage(image);
            }
        }

        return productMapper.toProductResponse(productRepository.save(product));
    }

    @Transactional
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        productRepository.deleteById(id);
    }
}
