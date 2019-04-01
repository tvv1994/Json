import dao.UserRepository;
import models.User;
import utils.JsonReader;

import java.util.List;

public class Solution {

    public static final String DB_URL = "jdbc:h2:" + Solution.class.getClassLoader().getResource("stockExchange").getPath();
    public static final String DB_DRIVER = "org.h2.Driver";

    public static void main(String[] args) throws Exception {
        JsonReader jsonReader = new JsonReader();
        //File file = new File("src\\main\\resourses\\input.json");
        // Хорошим тоном принято размещать различные ресурсы в директории, отличной от
        // директории, где лежат пакеты с исходниками
        // Solution.class.getClassLoader() мы вынуждены делать,
        // т.к. вызов происходит из статичного метода, в случае, если мы хотим получить
        // класслоадер у инстанциированного класса - мы можем вызвать просто this.getClass()
        List<User> userList = jsonReader.createUserList();
        // самый простой пример лямбда функции - forEach над коллекцией,
        // если упростить описание лямбд, то можно свести все к тому,
        // что к каждому элементу коллекции list применяется функция после ->
        // в нашем случае мы говорим, что каждый элемент из list будем передаваться в функцию
        // {System.out.println(user.getName())}, как параметр user
        userList.forEach(user -> System.out.println(user.getName()));
        // вот так выглядит forEach без "синтаксического сахара" лямбда-выражений
        //list.forEach(new Consumer<models.User>() {
        //@Override
        //public void accept(models.User user) {
        //System.out.println(user.getName());
        //}
        //});
        Class.forName(DB_DRIVER);
        UserRepository userRepository = new UserRepository(DB_URL);
        userRepository.saveList(userList);
        System.out.println(userRepository.getById(2).toString());
        userRepository.delete(10);
    }
}
