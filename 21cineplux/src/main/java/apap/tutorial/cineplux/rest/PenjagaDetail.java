package apap.tutorial.cineplux.rest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class PenjagaDetail {

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("count")
    private Integer count;
}
