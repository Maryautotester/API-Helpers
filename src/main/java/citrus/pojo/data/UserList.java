
package citrus.pojo.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserList {
    @JsonProperty("age")
    private Long age;
    @JsonProperty("course")
    private String course;
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;

    @Override
    public String toString() {
        return "{\n"
                + "\"name\":\"" + name + "\","
                + "\"course\":\"" + course + "\","
                + "\"email\":\"" + email + "\","
                + "\"age\":" + age + "\n"
                + "}";
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
