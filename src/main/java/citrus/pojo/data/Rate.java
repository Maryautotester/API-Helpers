
package citrus.pojo.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
    @Override
    public String toString() {
        return "Data{"
                + "name=" + name
                + ", price='" + score +'\''
                + '}';
    }
    @JsonProperty("name")
    private String name;
    @JsonProperty("score")
    private Long score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

}
