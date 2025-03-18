package com.sitra.usermanagement.util;


import com.sitra.usermanagement.dto.AddressDTO;
import com.sitra.usermanagement.dto.UserRequestDTO;
import com.sitra.usermanagement.dto.UserResponseDTO;
import com.sitra.usermanagement.entity.User;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Utility class for converting between {@link UserRequestDTO}, {@link UserResponseDTO}, and {@link User} entity.
 * <p>
 * This class provides static methods to convert:
 * - A {@link UserRequestDTO} to a {@link User} entity.
 * - A {@link User} entity to a {@link UserResponseDTO}.
 * </p>
 * <p>
 * It also handles conversion of associated addresses using {@link AddressConverter}.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 */
public class UserConverter {
    /**
     * Converts a {@link UserRequestDTO} to a {@link User} entity.
     * <p>
     * This method maps DTO fields to a {@link User} entity. It also converts and
     * associates the address list, if provided.
     * </p>
     *
     * @param dto The {@link UserRequestDTO} containing user details.
     * @return The converted {@link User} entity.
     */
    public static User convertToEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmailAddress(dto.getEmailAddress());
        //user.setSupervisor(supervisor);
        user.setSupervisorUserId(dto.getSupervisorUserId());
        user.setTitleText(dto.getTitleText());

        if (dto.getAddresses() != null) {
            user.setAddresses(dto.getAddresses().stream()
                    .map(addressDTO -> AddressConverter.convertToEntity(addressDTO, user))
                    .collect(Collectors.toList()));

        }
        return user;
    }
    /**
     * Converts a {@link User} entity to a {@link UserResponseDTO}.
     * <p>
     * This method extracts user details and converts the address list, if present.
     * It also ensures that nullable fields are handled properly.
     * </p>
     *
     * @param user The {@link User} entity to be converted.
     * @return The corresponding {@link UserResponseDTO}, or {@code null} if the input entity is {@code null}.
     */
    public static UserResponseDTO convertToDTO(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmailAddress(user.getEmailAddress());
        dto.setSupervisorUserId(user.getSupervisorUserId());
        dto.setTitleText(user.getTitleText());
        dto.setCreateUserId(user.getCreateUserId() != null ? user.getCreateUserId().longValue() : null);
        dto.setCreateDttm(user.getCreateDttm());
        dto.setUpdateUserId(user.getUpdateUserId() != null ? user.getUpdateUserId().longValue() : null);
        dto.setUpdateDttm(user.getUpdateDttm());

        // Convert Addresses List
        if (user.getAddresses() != null) {
            List<AddressDTO> addressDTOs = user.getAddresses().stream()
                    .map(AddressConverter::convertToDTO)
                    .collect(Collectors.toList());
            dto.setAddresses(addressDTOs);
        }

        return dto;
    }
}
