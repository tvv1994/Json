
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;
import java.sql.*;

public class Solution {
    public static final String DB_URL="jdbc:h2:./src/main/resources/stockExchange";
    public static final String DB_Driver="org.h2.Driver";
    public static final String createTable="create table IF NOT EXISTS USERS (name VARCHAR(50), age INT, isRabbit BOOLEAN, skills VARCHAR(150));";

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        //File file = new File("src\\main\\resourses\\input.json");
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
        //list.forEach(new Consumer<User>() {
            //@Override
            //public void accept(User user) {
                //System.out.println(user.getName());
            //}
        //});
        Class.forName(DB_Driver);
        Connection connection=DriverManager.getConnection(DB_URL);
        //Таблица
        Statement stH2 = connection.createStatement();
        stH2.execute(createTable);
        //Данные
        for(User e:list){
            String insert = creatString(e);
            stH2.execute(insert);
        }
    }

    public static String creatString(User u){
        String skill=String.join(", ", u.getSkills());
        String s= String.format("insert into USERS (name, age, isRabbit, skills) values ('%s', %d, %b, '%s');", u.getName(), u.getAge(), u.isRabbit(), skill);
        return s;
    }
}
