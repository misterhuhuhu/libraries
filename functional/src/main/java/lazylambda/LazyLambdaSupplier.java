package lazylambda;

import java.util.function.Supplier;

public class LazyLambdaSupplier<T> extends LambdaSupplier<T> {

    private T data;

    public LazyLambdaSupplier(Supplier<T> expensiveData) {
        super(expensiveData);
    }
    
    /**
     * get()只调用一次
     * @return
     */
    @Override
    public T getData() {
        if (data != null) {
            return data;
        }
        return data = expensiveData.get();
    }

}
