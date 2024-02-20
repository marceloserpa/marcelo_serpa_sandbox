package com.marceloserpa.dop.search;

import java.util.List;

public record PageResult(String term, List<Book> books, int items) {
}
