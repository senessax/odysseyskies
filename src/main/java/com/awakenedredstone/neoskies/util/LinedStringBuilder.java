package com.awakenedredstone.neoskies.util;

public class LinedStringBuilder {
    private final StringBuilder stringBuilder = new StringBuilder();

    public LinedStringBuilder append(String... content) {
        for (String s : content) {
            stringBuilder.append(s);
        }
        return this;
    }

    public LinedStringBuilder appendLine(String... content) {
        if (!stringBuilder.isEmpty()) append("\n");
        append(content);
        return this;
    }

    public LinedStringBuilder append(Object... content) {
        for (Object o : content) {
            stringBuilder.append(o);
        }
        return this;
    }

    public LinedStringBuilder appendLine(Object... content) {
        if (!stringBuilder.isEmpty()) append("\n");
        append(content);
        return this;
    }

    public LinedStringBuilder appendLine(String content) {
        if (!stringBuilder.isEmpty()) stringBuilder.append("\n");
        stringBuilder.append(content);
        return this;
    }

    public LinedStringBuilder appendLine(Object content) {
        return appendLine(String.valueOf(content));
    }

    public LinedStringBuilder appendLine() {
        stringBuilder.append("\n");
        return this;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
