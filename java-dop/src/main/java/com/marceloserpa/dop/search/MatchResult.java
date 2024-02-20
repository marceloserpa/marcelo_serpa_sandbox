package com.marceloserpa.dop.search;

import java.util.List;

sealed interface MatchResult<T> {
    record NoMatch<T>() implements MatchResult<T> { }
    record ExactMatch<T>(T entity) implements MatchResult<T> { }

    record ManyMatch<T>(List<T> entities) implements MatchResult<T> { }

}
