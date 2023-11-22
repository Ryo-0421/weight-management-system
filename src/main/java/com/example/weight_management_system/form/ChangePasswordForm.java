package com.example.weight_management_system.form;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Objects;

@Data
public class ChangePasswordForm {

    @NotBlank
    @Size(min=4, max=10)
    @Pattern(regexp="^[0-9a-zA-Z]+$")
    private String currentPassword;

    @NotBlank
    @Size(min=4, max=10)
    @Pattern(regexp="^[0-9a-zA-Z]+$")
    private String newPassword;

    @NotBlank
    @Size(min=4, max=10)
    @Pattern(regexp="^[0-9a-zA-Z]+$")
    private String confirmPassword;

    @AssertTrue(message="パスワードが一致しません")
    public boolean isSamePassword() {
        return Objects.equals(this.newPassword, this.confirmPassword);
    }
}
