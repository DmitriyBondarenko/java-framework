package io.techstack.steps;

import io.cucumber.java.en.When;
import io.techstack.dto.User;

public class Steps {
    private final User user;

    public Steps(User user) {
        this.user = user;
    }

    @When("I call Sanya")
    public void iCallSanya() {
        System.out.println(user.toString());
    }
}
