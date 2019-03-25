
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src\\main\\java\\input.json");
        List<User>list=mapper.readValue(file, new TypeReference<List<User>>(){});

        //System.out.println(list.get(1).getName());

    }
}
