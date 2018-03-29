package com.base.jackson;

import com.fasterxml.jackson.databind.ObjectReader;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;

/**
 * Created by Manish on 23/01/17.
 */

final class JacksonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final ObjectReader adapter;

    JacksonResponseBodyConverter(ObjectReader adapter) {
        this.adapter = adapter;
    }

    @Override public T convert(ResponseBody value) throws IOException {
        try {
            return adapter.readValue(value.charStream());
        } finally {
            value.close();
        }
    }
}
