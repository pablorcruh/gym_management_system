package ec.com.pablorcruh.gym_management_system.domain.models;

import ec.com.pablorcruh.gym_management_system.domain.enums.ContactType;

import java.util.List;

public class Campus {
    private String name;
    private String ruc;
    private List<ContactType> contacts;
    private List<Partner> partners ;
    private List<Campaign> campaigns;

}
