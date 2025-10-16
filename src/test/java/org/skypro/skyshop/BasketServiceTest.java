package org.skypro.skyshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.service.BasketService;
import org.skypro.skyshop.model.service.StorageService;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {
    @Mock
    private StorageService storageService;
    @Mock
    private ProductBasket productBasket;
    @InjectMocks
    private BasketService basketService;


    @Test
    public void basketServiceAddProductDoesNotExist_ThrowException() {

//        Exception thrownException = null;
//        try {
//            basketService.addProduct(UUID.randomUUID());
//        } catch (Exception e) {
//            thrownException = e;
//        }
//        assertThat(thrownException)
//                .isNotNull()
//                .isExactlyInstanceOf(NoSuchProductException.class);

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(UUID.randomUUID()));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void basketServiceAddProductStartsAddProductBasket(int prodNum) {
        Map<UUID, Product> productMap = new HashMap<>();
        Product[] product = new Product[5];
        product[0] = new SimpleProduct("Сахар", UUID.randomUUID(), 80);
        product[1] = new SimpleProduct("Сыр", UUID.randomUUID(), 600);
        product[2] = new FixPriceProduct("Молоко", UUID.randomUUID(), 70);
        product[3] = new SimpleProduct("Соль", UUID.randomUUID(), 20);
        product[4] = new DiscountedProduct("Овсянка", UUID.randomUUID(), 200, 40);

        productMap.put(product[0].getId(), product[0]);
        productMap.put(product[1].getId(), product[1]);
        productMap.put(product[2].getId(), product[2]);
        productMap.put(product[3].getId(), product[3]);
        productMap.put(product[4].getId(), product[4]);

        when(storageService.getProductById(product[prodNum].getId())).thenReturn(Optional.of(product[prodNum]));

        basketService.addProduct(product[prodNum].getId());
        Mockito.verify(productBasket, Mockito.atLeastOnce()).addProdToBasket(product[prodNum].getId());
    }

    @Test
    public void basketServiceGetUserBasketReturnsEmptyWhenProductBasketIsEmpty() {
        when(productBasket.getProdBasket()).thenReturn(Collections.emptyMap());
        Assertions.assertTrue(basketService.getUserBasket().getUserBasket().isEmpty());

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5})
    public void basketServiceGetUserBasketReturnsCorrectWhenProductBasketIsNotEmpty(int quantity) {
        Map<UUID, Integer> productMap = new HashMap<>();

        Product product = new SimpleProduct("Сахар", UUID.randomUUID(), 80);

        productMap.put(product.getId(), quantity);

        when(productBasket.getProdBasket()).thenReturn(productMap);
        when(storageService.getProductById(product.getId())).thenReturn(Optional.of(product));

        assertThat(basketService.getUserBasket())
                .isNotNull()
                .hasFieldOrPropertyWithValue("total", quantity * product.getPrice());

        assertThat(basketService.getUserBasket().getUserBasket().get(0).getItem().getId())
                .isNotNull()
                .isEqualTo(product.getId());

        assertThat(basketService.getUserBasket().getUserBasket().get(0).getQuantity())
                .isNotNull()
                .isEqualTo(quantity);


    }
    @Test
    public void basketServiceWhenProductBasketGetProdBasketIsNull() {
        when(productBasket.getProdBasket()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> basketService.getUserBasket().getUserBasket().isEmpty());
    }
}
