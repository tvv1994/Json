package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class JsonReader {

    private URL resource = JsonReader.class.getClassLoader().getResource("input.json");

    public JsonReader() {
    }

    public List<User> createUserList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<User> list = mapper.readValue(resource, new TypeReference<List<User>>() {
        });
        return list;
    }
}
