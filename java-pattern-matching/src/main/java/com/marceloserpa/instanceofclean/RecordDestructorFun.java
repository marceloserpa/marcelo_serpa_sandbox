package com.marceloserpa.instanceofclean;

public class RecordDestructorFun {

    record Point(int x, int y){};

    static class DestructorPoC {

        void printPointLegacy(Object object) {
            if(object instanceof Point) {
                Point point = (Point) object;
                int x = point.x;
                int y = point.y;
                System.out.printf("X is %d, y is %d%n", x, y);
            }
        }

        void printPointJava16(Object object) {
            if(object instanceof Point point) {
                int x = point.x;
                int y = point.y;
                System.out.printf("X is %d, y is %d%n", x, y);
            }

        }

        void printPointJava21(Object object) {
            if(object instanceof Point(int x, int y)) {
                System.out.printf("X is %d, y is %d%n", x, y);
            }

        }

    }


    public static void main(String[] args) {

        var poc = new DestructorPoC();

        var point = new Point(1,2);
        poc.printPointLegacy(point);
        poc.printPointJava16(point);
        poc.printPointJava21(point);

    }
}
