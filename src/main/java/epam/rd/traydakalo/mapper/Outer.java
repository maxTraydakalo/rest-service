package epam.rd.traydakalo.mapper;

public class Outer {
    private String A;
    private Outer() {
    }

    public static Nested getNested(){
        return new Nested();
    }

    public static class Nested {
        private Nested() {
        }

        public Outer build(){
            Outer outer = new Outer();
            outer.A = "a";
            return outer;
        }
    }
}
