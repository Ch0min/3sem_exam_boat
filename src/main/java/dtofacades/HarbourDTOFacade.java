package dtofacades;

import datafacades.HarbourFacade;
import datafacades.OwnerFacade;
import dtos.HarbourDTO;
import dtos.OwnerDTO;
import errorhandling.API_Exception;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class HarbourDTOFacade {

    private static HarbourFacade harbourFacade;
    private static HarbourDTOFacade instance;

    public HarbourDTOFacade() {
    }

    public static HarbourDTOFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            harbourFacade = HarbourFacade.getHarbourFacade(_emf);
            instance = new HarbourDTOFacade();
        }
        return instance;
    }

    public List<HarbourDTO> getAllHarbours() throws API_Exception {
        return HarbourDTO.getHarbourDTOs(harbourFacade.getAllHarbours());
    }

    public HarbourDTO getHarbourByHarbourName(String harbourName) throws API_Exception {
        return new HarbourDTO(harbourFacade.getHarbourByHarbourName(harbourName));
    }
}
