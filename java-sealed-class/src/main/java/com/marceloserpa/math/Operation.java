package com.marceloserpa.math;

public sealed interface Operation permits Sub, Sum{

    int exec(int x, int y);

}
