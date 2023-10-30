package com.itsmerino.productprices.shared;

public interface ConverterMultiSource<S1, S2, S3, T> {

    T convert(S1 source1, S2 source2, S3 source3);
}
