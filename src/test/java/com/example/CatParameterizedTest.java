package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class CatParameterizedTest {

    @Mock
    private Feline felineMock;

    private final List<String> expectedFood;


    public CatParameterizedTest(List<String> expectedFood) {
        this.expectedFood = expectedFood;
    }

    // Параметры – разные списки еды
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {List.of("Животные", "Птицы", "Рыба")},
                {List.of("Мыши", "Птицы")},
                {List.of("Трава")},
                {List.of()}
        });
    }

    @Test
    public void getFood_shouldReturnExpectedListFromFeline() throws Exception {
        MockitoAnnotations.initMocks(this);

        // Настраиваем мок
        when(felineMock.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(felineMock);
        List<String> actualFood = cat.getFood();

        assertEquals(expectedFood, actualFood);
        verify(felineMock, times(1)).eatMeat();
    }
}
