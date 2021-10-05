package com.bgt.pqr.utils;

import com.bgt.pqr.exceptions.PQRException;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Map;

public final class ParamsMapper {

    private ParamsMapper() {}

    public static String mapString(final Map<String, String> params, final String name) {
        return mapString(params, name, Boolean.FALSE);
    }

    public static String mapString(final Map<String, String> params, final String name, final boolean required) {
        final String value = StringUtils.trimToNull(params.get(name));
        final String rest = throwIfRequired(name, value, required);
        return rest;
    }

    public static Date mapDate(final Map<String, String> params, final String name) {
        return mapDate(params, name, Boolean.FALSE);
    }

    public static Date mapDate(final Map<String, String> params, final String name, final boolean required) {
        try {
            final String value = StringUtils.trimToNull(params.get(name));
            return throwIfRequired(name,
                    value == null ? null : new Date(Long.valueOf(value)), required);
        } catch (final NumberFormatException exception) {
            throw new PQRException(
                    String.format("Invalid date value for the parameter '%s'.", name));
        }
    }

    private static <T> T throwIfRequired(final String name, final T value, final boolean required) {
        if (required && (value == null)) {
            throw new PQRException(String.format("Parametro %s nulo ",name));
        }
        return value;
    }

    public static Long mapLong(final Map<String, String> params, final String name) {
        final Long aLong = mapLong(params, name, Boolean.FALSE);
        return aLong;
    }

    public static Long mapLong(final Map<String, String> params, final String name, final boolean required) {
        try {
            final String value = StringUtils.trimToNull(params.get(name));
            return throwIfRequired(name, value == null ? null : Long.valueOf(value), required);
        } catch (final NumberFormatException exception) {
            throw new PQRException(
                    String.format("Invalid numeric value for the parameter '%s'.", name));
        }
    }
}

