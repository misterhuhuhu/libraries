要创建和使用 MethodHandle，需要 4 个步骤
- Creating the lookup 创建lookup
- Creating the method type 创建method type
- Finding the method handle 查找method handle
- Invoking the method handle 调用method handle



1.  创建lookup

   1. 公共方法的访问

      ```
      MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
      ```

   2. 访问私有和受保护的方法

      ```
      MethodHandles.Lookup lookup = MethodHandles.lookup();
      ```

      

2. 创建method type

   1. 该 MethodType 将 java.util.List 类指定为返回类型，将 Object 数组指定为输入类型：

      ```
      MethodType mt = MethodType.methodType(List.class, Object[].class);
      ```

   2. 返回原始类型或 void 作为其返回类型，我们将使用表示这些类型的类（void.class、int.class ...）

3. 查找method handle

   1. 根据 String 类的 concat（） 方法创建一个：

      ```
      MethodType mt = MethodType.methodType(String.class, String.class);
      MethodHandle concatMH = publicLookup.findVirtual(String.class, "concat", mt);
      ```

   2. 静态方法

      ```
      MethodType mt = MethodType.methodType(List.class, Object[].class);
      
      MethodHandle asListMH = publicLookup.findStatic(Arrays.class, "asList", mt);
      ```

   3. 构造函数

      ```java
      MethodType mt = MethodType.methodType(void.class, String.class);
      
      MethodHandle newIntegerMH = publicLookup.findConstructor(Integer.class, mt);
      ```

   4.  字段的方法句柄

      ```
      MethodHandle getTitleMH = lookup.findGetter(Book.class, "title", String.class);
      ```

   5. 为私有方法创建方法句柄

      ``` 
      private String formatBook() {
          return id + " > " + title;
      }
      Method formatBookMethod = Book.class.getDeclaredMethod("formatBook");
      formatBookMethod.setAccessible(true);
      
      MethodHandle formatBookMH = lookup.unreflect(formatBookMethod);
      ```

4. 调用method handle

   1. invoke（）

      ```
      MethodType mt = MethodType.methodType(String.class, char.class, char.class);
      MethodHandle replaceMH = publicLookup.findVirtual(String.class, "replace", mt);
      
      String output = (String) replaceMH.invoke("jovo", Character.valueOf('o'), 'a');
      
      assertEquals("java", output);
      ```

      

   2. invokeWithArugments（） 

      包括拆装箱，如果使用  **invokeExact**，报错  java.lang.invoke.WrongMethodTypeException: expected (Object[])List but found (int,int)List

      ```
      MethodType mt = MethodType.methodType(List.class, Object[].class);
      MethodHandle asList = publicLookup.findStatic(Arrays.class, "asList", mt);
      
      List<Integer> list = (List<Integer>) asList.invokeWithArguments(1,2);
      
      assertThat(Arrays.asList(1,2), is(list));
      ```

      

   3. invokeExact（）

      不包括拆装箱

      ```
      MethodType mt = MethodType.methodType(int.class, int.class, int.class);
      MethodHandle sumMH = lookup.findStatic(Integer.class, "sum", mt);
      
      int sum = (int) sumMH.invokeExact(1, 11);
      
      assertEquals(12, sum);
      ```

#  使用数组

MethodHandle 不仅用于字段或对象，还用于数组。事实上，使用 asSpreader（） API，可以制作数组扩展方法句柄。

在这种情况下，方法句柄接受数组参数，将其元素扩展为位置参数，并选择数组的长度。

```
MethodType mt = MethodType.methodType(boolean.class, Object.class);
MethodHandle equals = publicLookup.findVirtual(String.class, "equals", mt);

MethodHandle methodHandle = equals.asSpreader(Object[].class, 2);

assertTrue((boolean) methodHandle.invoke(new Object[] { "java", "java" }));
```

```java
MethodHandle equals = publicLookup().findVirtual(String.class, "equals", methodType(boolean.class, Object.class));assert((boolean)equals.invokeExact("me",(Object)"me"));assert(!(boolean)equals.invokeExact("me",(Object)"thee"));
    // spread both arguments from a 2-array:
    MethodHandle eq2 = equals.asSpreader(Object[].class,
            2);assert((boolean)eq2.invokeExact(new Object[]{"me","me"}));assert(!(boolean)eq2.invokeExact(new Object[]{"me","thee"}));
    // try to spread from anything but a 2-array:
    for(
    int n = 0;n<=10;n++)
    {
        Object[] badArityArgs = (n == 2 ? new Object[0] : new Object[n]);
        try {
            assert ((boolean) eq2.invokeExact(badArityArgs) && false);
        } catch (IllegalArgumentException ex) {
        }
        // OK
    }
    // spread both arguments from a String array:
    MethodHandle eq2s = equals.asSpreader(String[].class,
            2);assert((boolean)eq2s.invokeExact(new String[]{"me","me"}));assert(!(boolean)eq2s.invokeExact(new String[]{"me","thee"}));
    // spread second arguments from a 1-array:
    MethodHandle eq1 = equals.asSpreader(Object[].class,
            1);assert((boolean)eq1.invokeExact("me",new Object[]{"me"}));assert(!(boolean)eq1.invokeExact("me",new Object[]{"thee"}));
    // spread no arguments from a 0-array or null:
    MethodHandle eq0 = equals.asSpreader(Object[].class,
            0);assert((boolean)eq0.invokeExact("me",(Object)"me",new Object[0]));assert(!(boolean)eq0.invokeExact("me",(Object)"thee",(Object[]null));
    // asSpreader and asCollector are approximate inverses:
    for(
    int n = 0;n<=2;n++)
    {
        for (Class<?> a : new Class<?>[] { Object[].class, String[].class, CharSequence[].class }) {
            MethodHandle equals2 = equals.asSpreader(a, n).asCollector(a, n);
            assert ((boolean) equals2.invokeWithArguments("me", "me"));
            assert (!(boolean) equals2.invokeWithArguments("me", "thee"));
        }
    }
    MethodHandle caToString = publicLookup()  .findStatic(Arrays.class, "toString", methodType(String.class, char[].class));

    assertEquals("[A, B, C]", (String) caToString.invokeExact("ABC".toCharArray())); 
     MethodHandle caString3 = caToString.asCollector(char[].class, 3);  assertEquals("[A, B, C]", (String) caString3.invokeExact('A', 'B', 'C'));  MethodHandle caToString2 = caString3.asSpreader(char[].class, 2);  assertEquals("[A, B, C]", (String) caToString2.invokeExact('A', "BC".toCharArray()));
```

# 增强方法句柄

一旦我们定义了一个方法句柄，就可以通过将方法句柄绑定到参数来增强它，而无需实际调用它。

```
MethodType mt = MethodType.methodType(String.class, String.class);
MethodHandle concatMH = publicLookup.findVirtual(String.class, "concat", mt);

MethodHandle bindedConcatMH = concatMH.bindTo("Hello ");

assertEquals("Hello World!", bindedConcatMH.invoke("World!"));
```



