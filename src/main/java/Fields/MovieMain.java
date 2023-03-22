package Fields;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MovieMain {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//        Movie movie = (Movie) createInstance(Movie.class, "Lord of the Rings", 2001, 12.90, true, Category.Adventure);
        Movie movie = new Movie("Lord of the Rings", 2001, 12.90, true, Category.Adventure);
        printDeclaredFieldsInfo(Movie.class, movie);
    }

    public static Object createInstance(Class<?> clazz, Object ...args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for(Constructor<?> constructor : clazz.getDeclaredConstructors()){
            if(constructor.getParameterTypes().length == args.length){
                constructor.newInstance(args);
            }
        }
        return null;
    }

    public static <T> void printDeclaredFieldsInfo(Class<? extends T> clazz, T instance) throws IllegalAccessException {
        for(Field field : clazz.getDeclaredFields()) {
            System.out.println(String.format("Field name : %s type : %s",
                    field.getName(),
                    field.getType().getName()));
            field.setAccessible(true);
            System.out.println(String.format("Is synthetic field: %s",
                    field.isSynthetic()));
            System.out.println(String.format("Field value is: %s", field.get(instance)));
            System.out.println();
        }
    }

    public static class Movie extends Product{
        public static final double MINIMUM_PRICE = 10.99;
        private boolean isReleased;
        private Category category;
        private double actualPrice;

        public Movie(String name, int year, double price, boolean isReleased, Category category) {
            super(name, year);
            this.isReleased = isReleased;
            this.category = category;
            this.actualPrice = Math.max(price, MINIMUM_PRICE);
        }

        public boolean isReleased() {
            return isReleased;
        }

        public Category getCategory() {
            return category;
        }

        public double getActualPrice() {
            return actualPrice;
        }

        // nested class
        public class MovieStats{
            private double timesWatched;

            public MovieStats(double timesWatched){
                this.timesWatched = timesWatched;
            }

            public double getRevenue() {
                return timesWatched * actualPrice;
            }
        }
    }

    public static class Product {
        protected String name;
        protected int year;
        protected double actualPrice;

        public Product(String name, int year) {
            this.name = name;
            this.year = year;
        }
    }

    public enum Category {
        Adventure,
        Action,
        Comedy
    }
}
