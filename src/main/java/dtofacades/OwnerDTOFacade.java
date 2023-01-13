package dtofacades;

import datafacades.OwnerFacade;
import dtos.OwnerDTO;
import errorhandling.API_Exception;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class OwnerDTOFacade {

    private static OwnerFacade ownerFacade;
    private static OwnerDTOFacade instance;

    public OwnerDTOFacade() {
    }

    public static OwnerDTOFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            ownerFacade = OwnerFacade.getOwnerFacade(_emf);
            instance = new OwnerDTOFacade();
        }
        return instance;
    }

    public List<OwnerDTO> getAllOwners() throws API_Exception {
        return OwnerDTO.getOwnerDTOs(ownerFacade.getAllOwners());
    }

    public List<OwnerDTO> getOwnersByBoat(String boatName) throws API_Exception {
        return OwnerDTO.getOwnerDTOs(ownerFacade.getOwnersByBoat(boatName));
    }
}
