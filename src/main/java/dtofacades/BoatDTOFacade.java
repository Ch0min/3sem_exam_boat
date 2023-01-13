package dtofacades;

import datafacades.BoatFacade;
import dtos.BoatDTO;
import dtos.HarbourDTO;
import dtos.UserDTO;
import entities.Boat;
import errorhandling.API_Exception;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BoatDTOFacade {

    private static BoatFacade boatFacade;
    private static BoatDTOFacade instance;

    public BoatDTOFacade() {
    }

    public static BoatDTOFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            boatFacade = BoatFacade.getBoatFacade(_emf);
            instance = new BoatDTOFacade();
        }
        return instance;
    }

    public List<BoatDTO> getAllBoats() throws API_Exception {
        return BoatDTO.getBoatDTOs(boatFacade.getAllBoats());
    }

    public List<BoatDTO> getBoatsByHarbour(String harbourName) throws API_Exception {
        return BoatDTO.getBoatDTOs(boatFacade.getBoatsByHarbour(harbourName));
    }

    public BoatDTO createBoat(BoatDTO boatDTO) throws API_Exception {
        return new BoatDTO(boatFacade.createBoat(boatDTO.getEntity()));
    }

    public BoatDTO assignBoatToHarbour(int boatID, int harbourID) throws API_Exception {
        return new BoatDTO(boatFacade.assignBoatToHarbour(boatID, harbourID));
    }

    public BoatDTO updateBoat(BoatDTO boatDTO) throws API_Exception {
        return new BoatDTO(boatFacade.updateBoat(boatDTO.getEntity()));
    }

    public BoatDTO deleteBoat(int boatID) throws API_Exception {
        return new BoatDTO(boatFacade.deleteBoat(boatID));
    }
}
