package PLSBackOffice.Utilities.APIUtilities.PojoClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BasePojo {
    @JsonProperty("DETAILS")
    private Details DETAILS;
}
