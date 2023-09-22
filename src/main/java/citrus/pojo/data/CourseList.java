package citrus.pojo.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseList {

    @Override
    public String toString() {
        return "{"
                + "   \"name\":\"" + name + "\","
                + " \"price\":" + price
                + "}";
    }
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private Long price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
