package com.jvaldez.retoKruger.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidationEcuadorianCI implements ConstraintValidator<EcuadorianCI, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        int suma = 0;
        if (s == null || s.length() != 10) {
            return false;
        } else {
            int a[] = new int[s.length() / 2];
            int b[] = new int[(s.length() / 2)];
            int c = 0;
            int d = 1;
            for (int i = 0; i < s.length() / 2; i++) {
                a[i] = Integer.parseInt(String.valueOf(s.charAt(c)));
                c = c + 2;
                if (i < (s.length() / 2) - 1) {
                    b[i] = Integer.parseInt(String.valueOf(s.charAt(d)));
                    d = d + 2;
                }
            }

            for (int i = 0; i < a.length; i++) {
                a[i] = a[i] * 2;
                if (a[i] > 9) {
                    a[i] = a[i] - 9;
                }
                suma = suma + a[i] + b[i];
            }
            int aux = suma / 10;
            int dec = (aux + 1) * 10;
            if ((dec - suma) == Integer.parseInt(String.valueOf(s.charAt(s.length() - 1))))
                return true;
            else if (suma % 10 == 0 && s.charAt(s.length() - 1) == '0') {
                return true;
            } else {
                return false;
            }
        }
    }
}
