package dev._2lstudios.jelly.errors;

import dev._2lstudios.jelly.utils.NumberUtils;

public class ArgumentParserException extends Exception {
    public ArgumentParserException(final int index, final String required) {
        super(NumberUtils.formatNumber(index) + " argument must be a " + required);
    }
}
