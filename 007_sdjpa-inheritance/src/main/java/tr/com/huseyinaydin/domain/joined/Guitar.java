package tr.com.huseyinaydin.domain.joined;

import jakarta.persistence.Entity;

@Entity
public class Guitar extends Instrument {

    private Integer numberOfStrings;

    public Integer getNumberOfStrings() {
        return numberOfStrings;
    }

    public void setNumberOfStrings(Integer numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }
}