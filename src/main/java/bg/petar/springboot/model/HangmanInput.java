package bg.petar.springboot.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class HangmanInput {

    @NotBlank(message = "Input must consist of 1 letter.")
    @Size(max=1, message = "Input must consist of 1 letter.")
    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
