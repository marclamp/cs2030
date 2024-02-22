import java.util.function.Function;

class Log<T> {
    private final T t;
    private final String logString;

    private Log(T t) {
        this.t = t;
        this.logString = "";
    }
    
    private Log(T t, String string) {
        this.t = t;
        this.logString = string;
    }

    static <T> Log<T> of(T t) {
        return new Log<T>(t);
    }
    
    static <T> Log<T> of(T t, String string) {
        return new Log<T>(t, string);
    }

    public <R> Log<R> map(Function<? super T, ? extends R> mapper) {
        return new Log<R>(mapper.apply(this.t), this.logString);
    }

    public <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends R>> mapper ) {
        Log<? extends R> logR = mapper.apply(this.t);
        return new Log<R>(logR.t, this.logString + logR.logString); 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Log<?> log) {
            return this.t.equals(log.t);
        } else {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return "Log[" + this.t + "]\n" + this.logString;
    }
}