package lazylambda;

import java.util.function.Supplier;

public class LambdaSupplier<T> {

    protected final Supplier<T> expensiveData;

    public LambdaSupplier(Supplier<T> expensiveData) {
        this.expensiveData = expensiveData;
    }
    
    /**
     * get()多次调用
     * @return
     */
    public T getData() {
        return expensiveData.get();
    }
}
