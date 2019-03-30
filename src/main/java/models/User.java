package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    @JsonProperty
    private String name;
    @JsonProperty
    private int age;
    @JsonProperty
    private boolean isRabbit;
    @JsonProperty
    private List<String> skills;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isRabbit() {
        return isRabbit;
    }

    public List<String> getSkills() {
        return skills;
    }
}
