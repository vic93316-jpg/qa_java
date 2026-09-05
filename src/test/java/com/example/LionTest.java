package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Feline felineMock;


    @Test
    public void lionWithMaleSex_shouldHaveMane() throws Exception {
        Lion lion = new Lion("Самец", new Feline());
        assertTrue(lion.doesHaveMane());
    }

    @Test
    public void lionWithFemaleSex_shouldNotHaveMane() throws Exception {
        Lion lion = new Lion("Самка", new Feline());
        assertFalse(lion.doesHaveMane());
    }


    @Test
    public void getKittens_shouldReturnValueFromFeline() throws Exception {
        when(felineMock.getKittens()).thenReturn(3);
        Lion lion = new Lion("Самец", felineMock);
        assertEquals(3, lion.getKittens());
        verify(felineMock, times(1)).getKittens();
    }


    @Test
    public void getFood_shouldReturnValueFromFelineEatMeat() throws Exception {
        List<String> expectedFood = List.of();
        when(felineMock.getFood("Хищник")).thenReturn(expectedFood);
        Lion lion = new Lion("Самец", felineMock);
        assertEquals(expectedFood, lion.getFood());
        verify(felineMock, times(1)).getFood("Хищник");;
    }


    @Test(expected = Exception.class)
    public void lionWithInvalidSex_shouldThrowException() throws Exception {
        new Lion("Неизвестно", felineMock);
    }

    @Test
    public void lionWithInvalidSex_shouldThrowExceptionWithCorrectMessage() {
        try {
            new Lion("Неизвестно", felineMock);
            fail("Expected Exception was not thrown");
        } catch (Exception e) {
            assertEquals("Используйте допустимые значения пола животного - самец или самка", e.getMessage());
        }
    }
}