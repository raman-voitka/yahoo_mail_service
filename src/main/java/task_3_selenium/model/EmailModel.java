package task_3_selenium.model;

import lombok.Data;

@Data
public class EmailModel {
    private String addressee;
    private String subject;
    private String body;
}