package dev._2lstudios.jelly.errors;

public class I18nCommandException extends Exception {
    private final String key;

    public I18nCommandException(final String key, final String message) {
        super(message);
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}
