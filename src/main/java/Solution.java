
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.function.Consumer;

public class Solution {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        File file = new File("src\\main\\java\\input.json");
        // Хорошим тоном принято размещать различные ресурсы в директории, отличной от
        // директории, где лежат пакеты с исходниками
        // Solution.class.getClassLoader() мы вынуждены делать,
        // т.к. вызов происходит из статичного метода, в случае, если мы хотим получить
        // класслоадер у инстанциированного класса - мы можем вызвать просто this.getClass()
        URL resource = Solution.class.getClassLoader().getResource("input.json");
        List<User> list = mapper.readValue(resource, new TypeReference<List<User>>(){});

        // самый простой пример лямбда функции - forEach над коллекцией,
        // если упростить описание лямбд, то можно свести все к тому,
        // что к каждому элементу коллекции list применяется функция после ->
        // в нашем случае мы говорим, что каждый элемент из list будем передаваться в функцию
        // {System.out.println(user.getName())}, как параметр user
        list.forEach(user -> System.out.println(user.getName()));

        // вот так выглядит forEach без "синтаксического сахара" лямбда-выражений
        list.forEach(new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user.getName());
            }
        });
        //System.out.println(list.get(1).getName());
    }
}
