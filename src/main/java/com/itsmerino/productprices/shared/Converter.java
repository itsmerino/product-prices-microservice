package com.itsmerino.productprices.shared;

public interface Converter<S, T> {

    T convert (S source);
}
