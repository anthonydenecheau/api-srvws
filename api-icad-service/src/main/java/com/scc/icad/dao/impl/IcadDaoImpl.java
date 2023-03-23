package com.scc.icad.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.scc.icad.dao.IcadDao;
import com.scc.icad.domain.Dog;
import com.scc.icad.domain.Owner;
import com.scc.icad.utils.FormatterUtils;

import oracle.jdbc.OracleTypes;

@Repository
public class IcadDaoImpl implements IcadDao {

    private static final Logger log = LoggerFactory.getLogger(IcadDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Dog getIcadDogByToken(String token) {

        Dog _dog = null;

        try {
            final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withSchemaName("PKG_CHIENS")
                    .withProcedureName("CALLWSICAD")
                    .withoutProcedureColumnMetaDataAccess()
                    .useInParameterNames("PC_Identifiant")
                    .declareParameters(
                            new SqlParameter("PC_Identifiant", Types.VARCHAR),
                            new SqlOutParameter("PR_EDITION", OracleTypes.CURSOR));

            final MapSqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("PC_Identifiant", token);

            final Map map = simpleJdbcCall.execute(paramMap);
            final ArrayList dogs = (ArrayList) map.get("PR_EDITION");
            if (dogs != null) {
                for (Object dog : dogs) {

                    final LinkedCaseInsensitiveMap rs = (LinkedCaseInsensitiveMap) dog;
                    _dog = new Dog();
                    _dog.setValidity(FormatterUtils.handleStringNullValue(rs.get("VALIDE")));
                    /*
                    _dog.setName(retreiveName(rs));
                    _dog.setGender(FormatterUtils.handleStringNullValue(rs.get("CODSEXE")));
                    _dog.setBirthDate(FormatterUtils.handleStringNullValue(rs.get("DTNAI")));
                    _dog.setDeceasedDate(FormatterUtils.handleStringNullValue(rs.get("DTDECES")));
                    _dog.setDisable(FormatterUtils.handleStringNullValue(rs.get("INACTIVE")));
                    _dog.setTatoo(FormatterUtils.handleStringNullValue(rs.get("TATOUAGE")));
                    _dog.setChip(FormatterUtils.handleStringNullValue(rs.get("PUCE")));
                    _dog.setBreed(FormatterUtils.handleStringNullValue(rs.get("APPARENCERACIALE")));
                    _dog.setRenewalIdentification(FormatterUtils.handleStringNullValue(rs.get("REMPLACEMENT")));
                    
                    Owner _owner = new Owner();
                    _owner.setLastName(FormatterUtils.handleStringNullValue(rs.get("NOM")));
                    _owner.setFirstName(FormatterUtils.handleStringNullValue(rs.get("PRENOM")));
                    _owner.setFlat(FormatterUtils.handleStringNullValue(rs.get("ETG")));
                    _owner.setBuilding(FormatterUtils.handleStringNullValue(rs.get("IMMEUBLE")));
                    _owner.setAddress(retreiveAddress(rs));
                    _owner.setPostbox(FormatterUtils.handleStringNullValue(rs.get("BP")));
                    _owner.setZipCode(FormatterUtils.handleStringNullValue(rs.get("CODEPOS")));
                    _owner.setTown(FormatterUtils.handleStringNullValue(rs.get("VILLE")));
                    _owner.setCountry(FormatterUtils.handleStringNullValue(rs.get("CODPAYS")));
                    _dog.setOwner(_owner);
                    */
                }
            }

        } catch (Exception e) {
            log.error("getIcadDogByToken " + e.getMessage());
        }

        return _dog;
    }
    
    private String retreiveName (LinkedCaseInsensitiveMap rs) {
       if ("".equals(FormatterUtils.handleStringNullValue(rs.get("NOMNAI"))))
          return FormatterUtils.handleStringNullValue(rs.get("NOMA"));
       else 
          return FormatterUtils.handleStringNullValue(rs.get("NOMNAI"));
    }

    private String retreiveAddress (LinkedCaseInsensitiveMap rs) {
       String address = "";
       if (!"".equals(FormatterUtils.handleStringNullValue(rs.get("NUMVOIE"))))
          address = FormatterUtils.handleStringNullValue(rs.get("NUMVOIE")); 
       
       if (!"".equals(FormatterUtils.handleStringNullValue(rs.get("TYPEVOIE"))))
          address += ( "".equals(address) ? FormatterUtils.handleStringNullValue(rs.get("TYPEVOIE")) : " "+ FormatterUtils.handleStringNullValue(rs.get("TYPEVOIE")) ); 

       if (!"".equals(FormatterUtils.handleStringNullValue(rs.get("NOMVOIE"))))
          address += ( "".equals(address) ? FormatterUtils.handleStringNullValue(rs.get("NOMVOIE"))  : " "+ FormatterUtils.handleStringNullValue(rs.get("NOMVOIE")) ); 

       return address;
       
    }
}
