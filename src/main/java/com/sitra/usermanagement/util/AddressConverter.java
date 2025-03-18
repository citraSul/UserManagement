package com.sitra.usermanagement.util;

import com.sitra.usermanagement.dto.AddressDTO;
import com.sitra.usermanagement.entity.Address;
import com.sitra.usermanagement.entity.User;

/**
 * Utility class for converting between {@link AddressDTO} and {@link Address} entity.
 * <p>
 * This class provides static methods to convert an {@link AddressDTO} to an {@link Address} entity
 * and vice versa, ensuring proper mapping between DTOs and database entities.
 * </p>
 *
 * @author Sitra
 * @version 1.0
 */

public class AddressConverter {
    /**
     * Converts an {@link AddressDTO} to an {@link Address} entity.
     * <p>
     * This method maps DTO fields to an {@link Address} entity and associates it with a {@link User}.
     * If the {@code countryCode} field is null, it defaults to "US".
     * </p>
     *
     * @param dto  The {@link AddressDTO} containing address details.
     * @param user The {@link User} associated with the address.
     * @return The converted {@link Address} entity, or {@code null} if the input DTO is {@code null}.
     */
    public static Address convertToEntity(AddressDTO dto,User user) {
        if (dto == null) {
            return null;
        }
        Address address = new Address();
        address.setMobilePhone(dto.getMobilePhone());
        address.setOtherPhone(dto.getOtherPhone());
        address.setLocation(dto.getLocation());
        address.setStreetAddress(dto.getStreetAddress());
        address.setStreetAddress2(dto.getStreetAddress2());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        address.setCountryCode(dto.getCountryCode() != null ? dto.getCountryCode() : "US");
        address.setUser(user);
        return address;
    }
    /**
     * Converts an {@link Address} entity to an {@link AddressDTO}.
     * <p>
     * This method extracts address details from the entity and returns them as a DTO.
     * </p>
     *
     * @param address The {@link Address} entity to be converted.
     * @return The corresponding {@link AddressDTO}, or {@code null} if the input entity is {@code null}.
     */
    public static AddressDTO convertToDTO(Address address) {
        if (address == null) {
            return null;
        }
        return new AddressDTO(
                address.getMobilePhone(),
                address.getOtherPhone(),
                address.getLocation(),
                address.getStreetAddress(),
                address.getStreetAddress2(),
                address.getCity(),
                address.getState(),
                address.getPostalCode(),
                address.getCountryCode()
        );
    }
}
