package org.geektimes.configuration.microprofile.config;

import org.eclipse.microprofile.config.spi.Converter;

public class IntegerConverter implements Converter<Integer> {

    @Override
    public Integer convert(String value) throws IllegalArgumentException, NullPointerException {

        return Integer.valueOf(value);
    }
}
