package com.finder.application.service;

import com.finder.application.ports.in.FindMaxNumberUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class FindMaxNumberServiceTest {
    @InjectMocks
    private FindMaxNumberService findMaxNumberUseCase;

    static Stream<Arguments> provideArgumentsInvalid() {
        return Stream.of(
                Arguments.of(5, 6, 85),
                Arguments.of(123,124,4560)
        );
    }


    static Stream<Arguments> provideArgumentsValid() {
        return Stream.of(
                Arguments.of(7, 5, 12345,12339),
                Arguments.of(5, 0, 4,0),
                Arguments.of(10, 5, 15,15),
                Arguments.of(17, 8, 54321,54306),
                Arguments.of(499999993, 9, 1000000000,999999995),
                Arguments.of(10, 5, 187,185),
                Arguments.of(2, 0, 999999999,999999998)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsValid")
    void testFindMaxNumber(int x, int y, int n,int expected) {
        int result = findMaxNumberUseCase.findMaxNumber(x, y, n);
        assertEquals(expected, result);
    }



    @ParameterizedTest
    @MethodSource("provideArgumentsInvalid")
    void testFindMaxNumberThrowException(int x, int y, int n) {
        assertThrows(OutOfRangeException.class, () -> {
            findMaxNumberUseCase.findMaxNumber(x, y, n);
        });
    }


}
