package com.scc.health.dao.impl;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;

import com.scc.health.dao.HealthDao;
import com.scc.health.domain.Reviewer;
import com.scc.health.exception.RegisterStudyException;
import com.scc.health.template.RegisterEventObject;
import com.scc.health.utils.FormatterUtils;

import oracle.jdbc.OracleTypes;

@Repository
public class HealthDaoImpl implements HealthDao {

   private static final Logger log = LoggerFactory.getLogger(HealthDaoImpl.class);

   @Autowired
   private JdbcTemplate jdbcTemplate;

   @SuppressWarnings("rawtypes")
   @Override
   public List<Reviewer> getReaderMyPacsByIdDog(int idDog, int idMaladie) {

      final List<Reviewer> readers = new ArrayList<Reviewer>();

      try {
         final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("PKG_LECTEURS")
               .withProcedureName("LIRE_LECTEURS_MYPACS").withoutProcedureColumnMetaDataAccess()
               .useInParameterNames("PN_IdChien").useInParameterNames("PN_IdMaladie").declareParameters(
                     new SqlParameter("PN_IdChien", Types.NUMERIC), new SqlParameter("PN_IdMaladie", Types.NUMERIC),
                     new SqlOutParameter("PR_EDITION", OracleTypes.CURSOR));

         final MapSqlParameterSource paramMap = new MapSqlParameterSource().addValue("PN_IdChien", idDog)
               .addValue("PN_IdMaladie", idMaladie);

         final Map map = simpleJdbcCall.execute(paramMap);
         final ArrayList datas = (ArrayList) map.get("PR_EDITION");
         if (datas != null) {
            for (Object reader : datas) {
               final LinkedCaseInsensitiveMap rs = (LinkedCaseInsensitiveMap) reader;

               Reviewer _r = new Reviewer();
               _r.setId(((BigDecimal) rs.get("ID")).intValue());
               _r.setOrdinalNumber(((BigDecimal) rs.get("NUM_ORDRE")).intValue());
               _r.setLastName(FormatterUtils.handleStringNullValue(rs.get("NOM_VETERINAIRE")));
               _r.setFirstName(FormatterUtils.handleStringNullValue(rs.get("PRENOM_VETERINAIRE")));

               readers.add(_r);
            }
         }

      } catch (Exception e) {
         log.error("getReaderMyPacsByIdDog " + e.getMessage());
      }

      return readers;
   }

   @Override
   public boolean registerMyPacsStudy(RegisterEventObject event) throws RegisterStudyException {

      // Note : il semble que la version spring-boot-starter-jdbc ne gère pas les Types ORACLE
      //       >> utilisation du VARCHAR2
      int result = 1;

      try {
         log.info("event {}", event.toString());

         final SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withSchemaName("PKG_LECTEURS")
               .withProcedureName("ENREGISTRE_ETUDE_MYPACS").withoutProcedureColumnMetaDataAccess()
               .useInParameterNames("PC_NumTicket").useInParameterNames("PN_IdChien")
               .useInParameterNames("PC_MailProprietaire").useInParameterNames("PC_TelProprietaire")
               .useInParameterNames("PC_Maladies").declareParameters(new SqlParameter("PC_NumTicket", Types.VARCHAR),
                     new SqlParameter("PN_IdChien", Types.NUMERIC),
                     new SqlParameter("PC_MailProprietaire", Types.VARCHAR),
                     new SqlParameter("PC_TelProprietaire", Types.VARCHAR),
                     new SqlParameter("PC_Maladies", Types.VARCHAR));

         String diseases = Arrays.stream(event.getStudiesDiseases()).map(t -> t.value).collect(Collectors.joining(","));
         String mail = "";
         String tel = "";
         if (event.getOwner()!=null) {
            mail = (event.getOwner().getMail() == null ? "" : event.getOwner().getMail());
            tel = (event.getOwner().getPhoneNumber() == null ? "" : event.getOwner().getPhoneNumber());
         }
            
         final MapSqlParameterSource paramMap = new MapSqlParameterSource()
               .addValue("PC_NumTicket", event.getReference()).addValue("PN_IdChien", event.getDogId())
               .addValue("PC_MailProprietaire", mail)
               .addValue("PC_TelProprietaire", tel)
               .addValue("PC_Maladies", diseases);

         simpleJdbcCall.execute(paramMap);

      } catch (Exception e) {
         log.error("registerMyPacsStudy {} ", e.getMessage());
         if (e instanceof UncategorizedSQLException)
            throw new RegisterStudyException(RegisterEventObject.class, "reference",
                  String.valueOf(event.getReference()) + " : "
                        + FormatterUtils.formatMessageErreurOracle(e.getMessage()));
         else
            throw new RegisterStudyException(RegisterEventObject.class, "reference",
                  String.valueOf(event.getReference()) + " : " + e.getMessage());
      }

      return (result == 1 ? true : false);

   }

}
