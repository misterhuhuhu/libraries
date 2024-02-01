package java9.varhandles;



import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class VariableHandlesUnitTest {

    public int publicTestVariable = 1;
    private int privateTestVariable = 1;
    public int variableToSet = 1;
    public int variableToCompareAndSet = 1;
    public int variableToGetAndAdd = 0;
    public byte variableToBitwiseOr = 0;
    public List<String> stringList = new ArrayList<>(){{add("someString");}};
    
    @Test
    void stringListGeneric () throws NoSuchFieldException, IllegalAccessException {
        VarHandle STRING_LIST1 = MethodHandles.lookup().in(VariableHandlesUnitTest.class).findVarHandle(VariableHandlesUnitTest.class, "stringList", List.class);
        System.out.println(STRING_LIST1.varType());
        List o = (List<Integer>) STRING_LIST1.get(this);
        Type[] genericInterfaces = o.getClass().getGenericInterfaces();
        Arrays.stream(genericInterfaces).forEach(k-> System.out.println(k.getTypeName()));
        System.out.println(o.getClass().getGenericSuperclass().getTypeName());//获取泛型类型 java.util.ArrayList<java.lang.String>
        System.out.println(o.get(0));
    }
    
    @Test
    public void whenVariableHandleForPublicVariableIsCreated_ThenItIsInitializedProperly() throws NoSuchFieldException, IllegalAccessException {
        VarHandle PUBLIC_TEST_VARIABLE = MethodHandles
          .lookup()
          .in(VariableHandlesUnitTest.class)
          .findVarHandle(VariableHandlesUnitTest.class, "publicTestVariable", int.class);

        assertEquals(1, PUBLIC_TEST_VARIABLE.coordinateTypes().size());//这个 VarHandle 的 coordinateTypes 属性不是空的，它有一个元素，那就是我们的 VariableHandlesUnitTest 类
        assertEquals(VariableHandlesUnitTest.class, PUBLIC_TEST_VARIABLE.coordinateTypes().get(0));
    }

    @Test
    public void whenVariableHandleForPrivateVariableIsCreated_ThenItIsInitializedProperly() throws NoSuchFieldException, IllegalAccessException {
        VarHandle PRIVATE_TEST_VARIABLE = MethodHandles
          .privateLookupIn(VariableHandlesUnitTest.class, MethodHandles.lookup())//等于 MethodHandles.lookup().in(VariableHandlesUnitTest.class)
          .findVarHandle(VariableHandlesUnitTest.class, "privateTestVariable", int.class);

        assertEquals(1, PRIVATE_TEST_VARIABLE.coordinateTypes().size());
        assertEquals(VariableHandlesUnitTest.class, PRIVATE_TEST_VARIABLE.coordinateTypes().get(0));
    }

    @Test
    public void whenVariableHandleForArrayVariableIsCreated_ThenItIsInitializedProperly() throws NoSuchFieldException, IllegalAccessException {
        VarHandle arrayVarHandle = MethodHandles
          .arrayElementVarHandle(int[].class);

        assertEquals(2, arrayVarHandle.coordinateTypes().size());
        Class<?> aClass = arrayVarHandle.coordinateTypes().get(0);
        assertEquals(int[].class, aClass);
    }

    @Test
    public void givenVarHandle_whenGetIsInvoked_ThenValueOfVariableIsReturned() throws NoSuchFieldException, IllegalAccessException {
        VarHandle PUBLIC_TEST_VARIABLE = MethodHandles
          .lookup()
          .in(VariableHandlesUnitTest.class)
          .findVarHandle(VariableHandlesUnitTest.class, "publicTestVariable", int.class);

        assertEquals(1, (int) PUBLIC_TEST_VARIABLE.get(this));
    }

    @Test
    public void givenVarHandle_whenSetIsInvoked_ThenValueOfVariableIsChanged() throws NoSuchFieldException, IllegalAccessException {
        VarHandle VARIABLE_TO_SET = MethodHandles
          .lookup()
          .in(VariableHandlesUnitTest.class)
          .findVarHandle(VariableHandlesUnitTest.class, "variableToSet", int.class);

        VARIABLE_TO_SET.set(this, 15);//普通访问
        assertEquals(15, (int) VARIABLE_TO_SET.get(this));
    }

    @Test
    public void givenVarHandle_whenCompareAndSetIsInvoked_ThenValueOfVariableIsChanged() throws NoSuchFieldException, IllegalAccessException {
        VarHandle VARIABLE_TO_COMPARE_AND_SET = MethodHandles
          .lookup()
          .in(VariableHandlesUnitTest.class)
          .findVarHandle(VariableHandlesUnitTest.class, "variableToCompareAndSet", int.class);

        VARIABLE_TO_COMPARE_AND_SET.compareAndSet(this, 1, 100);// 原子访问
        assertEquals(100, (int) VARIABLE_TO_COMPARE_AND_SET.get(this));
    }

    @Test
    public void givenVarHandle_whenGetAndAddIsInvoked_ThenValueOfVariableIsChanged() throws NoSuchFieldException, IllegalAccessException {
        VarHandle VARIABLE_TO_GET_AND_ADD = MethodHandles
          .lookup()
          .in(VariableHandlesUnitTest.class)
          .findVarHandle(VariableHandlesUnitTest.class, "variableToGetAndAdd", int.class);

        int before = (int) VARIABLE_TO_GET_AND_ADD.getAndAdd(this, 200);//getAndAdd（） 方法首先返回变量的值，然后添加提供的值

        assertEquals(0, before);
        assertEquals(200, (int) VARIABLE_TO_GET_AND_ADD.get(this));
    }

    @Test
    public void givenVarHandle_whenGetAndBitwiseOrIsInvoked_ThenValueOfVariableIsChanged() throws NoSuchFieldException, IllegalAccessException {
        VarHandle VARIABLE_TO_BITWISE_OR = MethodHandles
          .lookup()
          .in(VariableHandlesUnitTest.class)
          .findVarHandle(VariableHandlesUnitTest.class, "variableToBitwiseOr", byte.class);
        byte before = (byte) VARIABLE_TO_BITWISE_OR.getAndBitwiseOr(this, (byte) 127);//将获取变量的值并对其执行按位 OR 运算

        assertEquals(0, before);
        assertEquals(127, (byte) VARIABLE_TO_BITWISE_OR.get(this));
    }
}
