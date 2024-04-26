package ec.com.pablorcruh.gym_management_system.domain.models;

import ec.com.pablorcruh.gym_management_system.domain.enums.MembershipType;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Membership {
    private BigInteger price;
    private LocalDateTime firstDay;
    private LocalDateTime lastDay;
    private MembershipType membershipType;
    private Boolean state;
    private String observations;
}
