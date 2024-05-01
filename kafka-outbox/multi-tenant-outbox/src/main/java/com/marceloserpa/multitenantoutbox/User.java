package com.marceloserpa.multitenantoutbox;

public record User(long tentantId, long userId, String username) {
}
