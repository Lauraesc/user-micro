package com.foodcourt.user_micro.adapters.driving.mapper;


import com.foodcourt.user_micro.adapters.driving.dto.request.LoginRequest;
import com.foodcourt.user_micro.domain.model.Auth;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface IAuthMapperDto {

    Auth toAuth(LoginRequest loginRequest);
    LoginRequest toLoginRequest(Auth auth);
}
