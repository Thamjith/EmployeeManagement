package com.inapp.EmployeeManagement.HelperClasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimpleResponse {
    private String message;
    private Boolean status;
}
