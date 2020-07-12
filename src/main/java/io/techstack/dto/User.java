package io.techstack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private String firstName;
    private String lastName;
    private int id;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("User name: ", firstName)
                .add("User job: ", lastName)
                .toString();
    }
}
