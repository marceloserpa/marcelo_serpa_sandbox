package com.marceloserpa.dop;

import java.nio.file.Path;

sealed interface Option {
    record InputFile(Path path) implements Option { }
    record OutputFile(Path path) implements Option { }
    record MaxLines(int maxLines) implements Option { }
    record PrintLineNumbers() implements Option { }
}
