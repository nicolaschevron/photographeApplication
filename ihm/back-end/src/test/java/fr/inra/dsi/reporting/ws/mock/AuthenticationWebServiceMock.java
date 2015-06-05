package fr.inra.dsi.reporting.ws.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.inra.dsi.reporting.data.impl.RapportListeActivitesParUniteBuilderImpl;
import fr.inra.dsi.reporting.dto.InfosAgent;
import fr.inra.dsi.reporting.dto.RoleAgent;
import fr.inra.dsi.reporting.exception.WebServiceException;
import fr.inra.dsi.reporting.util.TestUtil;
import fr.inra.dsi.reporting.ws.IAuthenticationWebService;

/**
 * Mock de IAuthenticationService.
 * @author gugau
 *
 */
@Service
public class AuthenticationWebServiceMock implements IAuthenticationWebService {
    
    public static String MOCK_NAME = "Dupond";
    public static String MOCK_FIRSTNAME = "Jean";

    public static String MOCK_LOGIN_ADMIN = "admin";
    public static String MOCK_MATRICULE_ADMIN = "999666Z";
    
	public static String MOCK_LOGIN = "test";
    public static String MOCK_MATRICULE = "123456Z";
    
    public static String MOCK_LOGIN_DU = "roleDu";
    public static String MOCK_NAME_DU = "Prunet";
    public static List<String> DU_CODE_UNITES = new ArrayList<String>();
    static{
        DU_CODE_UNITES.add("23");
        DU_CODE_UNITES.add("36");
    }
    
    public static String MOCK_FIRSTNAME_DU = "pierre";
    public static String MOCK_MATRICULE_DU = "789101Z";
    public static String MOCK_STRUCTURE_DU = "STRUCTURE";
    public static String MOCK_DU = "DU";
    
    public static String MOCK_LOGIN_CD = "roleCd";
    public static String MOCK_MATRICULE_CD = "18107G";
    public static List<String> CD_CODE_DEPT = new ArrayList<String>();
    static{
    	CD_CODE_DEPT.add("60");
    }
    
    public static String MOCK_LOGIN_CNOC = "roleCNOC";
    public static String MOCK_MATRICULE_CNOC = "18605Y";
    
    public static String MOCK_LOGIN_CNUE = "roleCNUE";
    public static String MOCK_MATRICULE_CNUE = "13594B";
    
    public static String MOCK_LOGIN_DAR = "roleDAR";
    public static String MOCK_MATRICULE_DAR = "140184Z";
    
    public static String MOCK_LOGIN_PC = "rolePC";
    public static String MOCK_MATRICULE_PC = "06423G";
    
    /**
     * Constructeur par défaut.
     */
    public AuthenticationWebServiceMock(){
        super();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoleAgent> getRolesByMatricule(String matricule)
            throws WebServiceException {
    	List<RoleAgent> result = new ArrayList<RoleAgent>();
    	if (MOCK_MATRICULE_DU.equals(matricule)){
    		RoleAgent roleDu = new RoleAgent();
        	roleDu.setCodeRole(RoleAgent.CODE_DU);
        	roleDu.setCodeStructure(RapportListeActivitesParUniteBuilderImpl.AC_U_CODE_UNITE);	
        	result.add(roleDu);

        	for(String codeStruct : DU_CODE_UNITES){
                roleDu = new RoleAgent();
                roleDu.setCodeRole(RoleAgent.CODE_DU);
                roleDu.setCodeStructure(codeStruct);  
                result.add(roleDu);
        	}
    	}
    	if (MOCK_MATRICULE_CD.equals(matricule)){
    		RoleAgent roleCd = new RoleAgent();
        	roleCd.setCodeRole(RoleAgent.CODE_CD);
        	roleCd.setCodeStructure(RapportListeActivitesParUniteBuilderImpl.AC_U_CODIQUE_DEPARTEMENT);	
        	result.add(roleCd);

        	for(String codeStruct : CD_CODE_DEPT){
                roleCd = new RoleAgent();
                roleCd.setCodeRole(RoleAgent.CODE_CD);
                roleCd.setCodeStructure(codeStruct);  
                result.add(roleCd);
        	}
    	}
    	if (MOCK_MATRICULE_CNOC.equals(matricule)){
    		RoleAgent roleCnoc = new RoleAgent();
    		roleCnoc.setCodeRole(RoleAgent.CODE_CNOC);
        	result.add(roleCnoc);
    	}
    	if (MOCK_MATRICULE_CNUE.equals(matricule)){
    		RoleAgent roleCnue = new RoleAgent();
    		roleCnue.setCodeRole(RoleAgent.CODE_CNUE);
        	result.add(roleCnue);
    	}
        if (MOCK_MATRICULE_DAR.equals(matricule)){
            RoleAgent roleDar = new RoleAgent();
            roleDar.setCodeRole(RoleAgent.CODE_DAR);
            roleDar.setCodeStructure(String.valueOf(TestUtil.getNextIdentifiantStructure()));
            result.add(roleDar);
        }
        if (MOCK_MATRICULE_PC.equals(matricule)){
            RoleAgent rolePc = new RoleAgent();
            rolePc.setCodeRole(RoleAgent.CODE_PC);
            rolePc.setCodeStructure(String.valueOf(TestUtil.getNextIdentifiantStructure()));
            result.add(rolePc);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InfosAgent getInfosAgentByLogin(String login)
            throws WebServiceException {
        InfosAgent result = new InfosAgent();
        if(MOCK_LOGIN_DU.equals(login)){
            result.setMatricule(MOCK_MATRICULE_DU);
	        result.setNom(MOCK_NAME_DU);
	        result.setPrenom(MOCK_FIRSTNAME_DU);
        } 
        else if(MOCK_LOGIN_CD.equals(login)) {
            result.setMatricule(MOCK_MATRICULE_CD);
            result.setNom(MOCK_NAME);
            result.setPrenom(MOCK_FIRSTNAME);
        }
        else if(MOCK_LOGIN_ADMIN.equals(login)) {
            result.setMatricule(MOCK_MATRICULE_ADMIN);
            result.setNom(MOCK_NAME);
            result.setPrenom(MOCK_FIRSTNAME);
        }else if(MOCK_LOGIN_CNOC.equals(login)) {
            result.setMatricule(MOCK_MATRICULE_CNOC);
            result.setNom(MOCK_NAME);
            result.setPrenom(MOCK_FIRSTNAME);
        }else if(MOCK_LOGIN_CNUE.equals(login)) {
            result.setMatricule(MOCK_MATRICULE_CNUE);
            result.setNom(MOCK_NAME);
            result.setPrenom(MOCK_FIRSTNAME);
        } else if(MOCK_LOGIN_DAR.equals(login)) {
            result.setMatricule(MOCK_MATRICULE_DAR);
            result.setNom(MOCK_NAME);
            result.setPrenom(MOCK_FIRSTNAME);
        } else if(MOCK_LOGIN_PC.equals(login)) {
            result.setMatricule(MOCK_MATRICULE_PC);
            result.setNom(MOCK_NAME);
            result.setPrenom(MOCK_FIRSTNAME);
        } else{
	        result.setMatricule(MOCK_MATRICULE);
	        result.setNom(MOCK_NAME);
	        result.setPrenom(MOCK_FIRSTNAME);
        }
        return result;
    }

}
