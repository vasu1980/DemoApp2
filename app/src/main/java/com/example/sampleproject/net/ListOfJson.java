package com.example.sampleproject.net;

import androidx.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ListOfJson<T> implements ParameterizedType
{
    private Class<?> wrapped;

    public ListOfJson(Class<T> wrapper) {
        this.wrapped = wrapper;
    }

    @NonNull
    @Override
    public Type[] getActualTypeArguments() {
        return new Type[] { wrapped };
    }

    @NonNull
    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}

