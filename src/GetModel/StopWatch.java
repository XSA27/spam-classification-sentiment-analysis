package GetModel;

public class StopWatch { 
    private final long start;

    public StopWatch() {
        start = System.currentTimeMillis();
    }
    
    public long elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start);
    }
} 
