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
    public void lionWithMaleSex_shouldHaveManeAndUseMockedMethods() throws Exception {
        when(felineMock.eatMeat()).thenReturn(List.of("Мясо", "Рыба"));
        when(felineMock.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", felineMock);

        assertTrue(lion.doesHaveMane());
        assertEquals(3, lion.getKittens());
        assertEquals(List.of("Мясо", "Рыба"), lion.getFood());

        verify(felineMock).eatMeat();
        verify(felineMock).getKittens();
    }


    @Test
    public void lionWithFemaleSex_shouldNotHaveMane() throws Exception {
        when(felineMock.eatMeat()).thenReturn(List.of("Трава"));
        when(felineMock.getKittens()).thenReturn(1);

        Lion lion = new Lion("Самка", felineMock);

        assertFalse(lion.doesHaveMane());
        assertEquals(1, lion.getKittens());
        assertEquals(List.of("Трава"), lion.getFood());
        verify(felineMock).eatMeat();
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

    @Test
    public void getKittens_shouldDelegateToFeline() throws Exception {
        when(felineMock.getKittens()).thenReturn(7);
        Lion lion = new Lion("Самец", felineMock);

        assertEquals(7, lion.getKittens());
        verify(felineMock, times(1)).getKittens();
    }

    @Test
    public void getFood_shouldDelegateToFelineEatMeat() throws Exception {
        List<String> expectedFood = List.of("Зебры", "Антилопы");
        when(felineMock.eatMeat()).thenReturn(expectedFood);

        Lion lion = new Lion("Самец", felineMock);
        assertEquals(expectedFood, lion.getFood());

        verify(felineMock, times(1)).eatMeat();
    }
}