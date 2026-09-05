package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Spy
    private Feline felineSpy;

    @Test
    public void eatMeat_shouldCallGetFoodWithPredatorAndReturnResult() throws Exception {
        List<String> expectedMeat = List.of("Мясо", "Птица");
        doReturn(expectedMeat).when(felineSpy).getFood("Хищник");

        List<String> actualMeat = felineSpy.eatMeat();

        assertEquals(expectedMeat, actualMeat);

        verify(felineSpy, times(1)).getFood("Хищник");
    }

    @Test(expected = Exception.class)
    public void eatMeat_shouldPropagateExceptionFromGetFood() throws Exception {
        doThrow(new Exception("Тестовое исключение")).when(felineSpy).getFood("Хищник");

        felineSpy.eatMeat();
    }

    @Test
    public void getKittens_shouldReturnOne() {
        assertEquals(1, felineSpy.getKittens());
    }

    @Test
    public void getKittensWithCount_shouldReturnGivenCount() {
        assertEquals(5, felineSpy.getKittens(5));
    }

    @Test
    public void getFamily_shouldReturnFelidae() {
        assertEquals("Кошачьи", felineSpy.getFamily());
    }
}