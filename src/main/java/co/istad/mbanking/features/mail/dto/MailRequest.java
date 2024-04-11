package co.istad.mbanking.features.mail.dto;

import jakarta.validation.constraints.NotBlank;

public record MailRequest(
        @NotBlank(message = "Email is requird")
                          String to,
        @NotBlank(message = "Email is requird")
        String subject,
        @NotBlank(message = "Email is requird")
        String text

) {

}
