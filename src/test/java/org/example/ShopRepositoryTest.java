package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopRepositoryTest {
    ShopRepository repo = new ShopRepository();
    Product product1 = new Product(1, "milk", 100);
    Product product2 = new Product(2, "bread", 50);
    Product product3 = new Product(3, "eggs", 80);
    Product product4 = new Product(4, "juice", 120);
    Product productExistingId = new Product(3, "rice", 70);

    @BeforeEach
    public void setup() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
    }

    @Test
    public void shouldRemoveExisting() {
        repo.remove(3);
        Product[] expected = {product1, product2};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(4);
        });
    }

    @Test
    public void shouldAddNew() {
        repo.add(product4);
        Product[] expected = {product1, product2, product3, product4};
        Product[] actual = repo.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnAlreadyExistsException() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(productExistingId);
        });
    }
}