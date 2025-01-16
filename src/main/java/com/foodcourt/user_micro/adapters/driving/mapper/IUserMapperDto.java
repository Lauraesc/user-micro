package com.foodcourt.user_micro.adapters.driving.mapper;


import com.foodcourt.user_micro.adapters.driving.dto.request.UserRequest;
import com.foodcourt.user_micro.adapters.driving.dto.response.UserResponseSave;
import com.foodcourt.user_micro.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

//"los datos que tenga uno y el otro no, ignorelos"
@Mapper(componentModel = "spring",
    unmappedSourcePolicy = org.mapstruct.ReportingPolicy.IGNORE,
    unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)

public interface IUserMapperDto {
        User userRequestToUser(UserRequest userRequest);
        UserResponseSave userToUserResponseSave(User user);

}
