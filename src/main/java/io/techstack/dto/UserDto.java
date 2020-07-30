package io.techstack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    public String firstName;
    public String lastName;
    public int id;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("User name: ", firstName)
                .add("User job: ", lastName)
                .toString();
    }
}
