package myJava.reflect;

public class InnerContainer {
    public InnerContainer() { }

    private class InnerA {
        private String f = InnerA.class.getSimpleName();
        public InnerA() { }
    }

    private static class InnerB {
        private String f = InnerB.class.getSimpleName();
        public InnerB() {}
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            System.out.println("Method run of Runnable r");
        }
    };
}
