package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Spy;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class FelineEatMeatParameterizedTest {

    @Spy
    private Feline felineSpy;

    private final List<String> expectedFood;

    public FelineEatMeatParameterizedTest(List<String> expectedFood) {
        this.expectedFood = expectedFood;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {List.of("Мясо", "Птица")},
                {List.of("Мыши", "Птицы", "Рыба")},
                {List.of("Трава")},
                {List.of()}
        });
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void eatMeat_shouldReturnExpectedListFromGetFood() throws Exception {
        doReturn(expectedFood).when(felineSpy).getFood("Хищник");

        List<String> actual = felineSpy.eatMeat();
        assertEquals(expectedFood, actual);
    }
}