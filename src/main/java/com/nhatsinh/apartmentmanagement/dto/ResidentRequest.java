package com.nhatsinh.apartmentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ResidentRequest(
    @NotBlank(message = "Tên không được để trống")
    @Size(min= 3, max = 50)
    String fullname,

    @NotBlank(message="số điện thoại không được để trống")
    @Size(max= 11)
    String phone,
    @NotNull(message = "User ID không được để trống ")
    Long userId,
     
    @NotNull(message = "Apartment ID không được để trống") 
    Long apartmentId
    
)
     { 
}
