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

    @Override
    public String toString() {
        String skill = String.join(", ", getSkills());
        return String.format("User - name: %s; age: %d; isRabbit: %b; skills: %s", name, age, isRabbit, skill);
    }
}
