package com.hichamea.todolist.dto;


import com.hichamea.todolist.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A Data Transfer Object (DTO) representing a user.
 * This class contains attributes for user information and conversion methods to and from the corresponding entity.
 */
@Data
@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String password;

    @JsonIgnore
    private List<CategoryDto> category;

    /**
     * Converts a UserDto object to a User entity.
     *
     * @param userDto The UserDto object to convert.
     * @return The corresponding User entity.
     */
    public static User toEntity(UserDto userDto) {
        final User user = new User();

        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCategory(
                userDto.getCategory() != null ? userDto.getCategory()
                                                       .stream()
                                                       .map(CategoryDto::toEntity)
                                                       .collect(Collectors.toList()) : null
        );

        return user;
    }

    /**
     * Converts a User entity to a UserDto object.
     *
     * @param user The User entity to convert.
     * @return The corresponding UserDto object.
     */
    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                      .id(user.getId())
                      .firstName(user.getFirstName())
                      .lastName(user.getLastName())
                      .userName(user.getUserName())
                      .password(user.getPassword())
                      .email(user.getEmail())
                      .category(
                              user.getCategory() != null ? user.getCategory()
                                                               .stream()
                                                               .map(CategoryDto::fromEntity)
                                                               .collect(Collectors.toList()) : null
                      )
                      .build();
    }
}
