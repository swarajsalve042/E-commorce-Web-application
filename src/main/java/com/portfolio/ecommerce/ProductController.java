package com.portfolio.ecommerce;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repo;
    public ProductController(ProductRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Product> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        validate(product);
        product.setName(product.getName().trim());
        return repo.save(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product changes) {
        Product existing = repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        validate(changes);
        existing.setName(changes.getName().trim());
        existing.setPrice(changes.getPrice());
        return repo.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repo.deleteById(id);
    }

    private void validate(Product product) {
        if (product.getName() == null || product.getName().isBlank()
                || product.getName().trim().length() > 100
                || product.getPrice() == null
                || product.getPrice().compareTo(BigDecimal.ZERO) <= 0
                || product.getPrice().scale() > 2
                || product.getPrice().precision() > 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "name (1-100 characters) and positive price (up to 2 decimals) are required");
        }
    }
}
