package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Domino {
    private final int left;
    private final int right;

    public Domino(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "[" + left + "|" + right + "]";
    }
}