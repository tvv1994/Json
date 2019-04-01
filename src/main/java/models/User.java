package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private int age;
    @JsonProperty
    private boolean isRabbit;
    @JsonProperty
    private List<String> skills;

    public User() {
    }
    public User(String name, int age, boolean isRabbit, List<String> skills) {
        this.name = name;
        this.age = age;
        this.isRabbit = isRabbit;
        this.skills = skills;
    }
    public User(int id, String name, int age, boolean isRabbit, List<String> skills) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isRabbit = isRabbit;
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

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
        return String.format("User: %d; name: %s; age: %d; isRabbit: %b; skills: %s", id ,name, age, isRabbit, skill);
    }
}
