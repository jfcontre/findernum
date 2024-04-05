package com.finder.application.service;

import com.finder.application.ports.in.FindMaxNumberUseCase;
import com.finder.domain.MaxIntegerWithRemainder;

public class FindMaxNumberService implements FindMaxNumberUseCase {
    @Override
    public int findMaxNumber(int valueX, int valueY, int valueN) {
        MaxIntegerWithRemainder fn = (x, y, n) -> n - (n - y) % x;

        //Validar 2 <= X <= 10^9
        validateRange(valueX, 2, (int) Math.pow(10, 9), "x");
        //Validar 0 <= Y < X
        validateRange(valueY, 0, valueX - 1, "y");
        //Validar y <= N <= 10^9
        validateRange(valueN, valueY, (int) Math.pow(10, 9), "n");

        return fn.find(valueX, valueY, valueN);
    }

    /**
     * Valida si el valor proporcionado se encuentra dentro del rango especificado por los valores mínimo y máximo.
     *
     * @param value El valor que se desea validar.
     * @param min   El valor mínimo permitido en el rango.
     * @param max   El valor máximo permitido en el rango.
     * @param name  El nombre del valor que se está validando (para propósitos informativos en el mensaje de excepción).
     * @throws IllegalArgumentException Si el valor está fuera del rango especificado.
     */
    private void validateRange(int value, int min, int max, String name) {
        if (value < min || value > max) {
            throw new OutOfRangeException("El valor de " + name + " está fuera del rango permitido: " + min + " <= " + name + " <= " + max);
        }
    }
}
