package com.scc.licence.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import com.scc.licence.Application;
import com.scc.licence.exceptions.EntityNotFoundException;
import com.scc.licence.model.Holder;
import com.scc.licence.services.LicenceService;
import com.scc.licence.template.LicenceObject;
import com.scc.licence.template.ResponseObjectList;
import com.scc.licence.utils.CommissionEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.zipkin.enabled: false" })
@AutoConfigureMockMvc
public class LicenceControllerTest {

   // [TODO] : TEST A ECRIRE ! 
   
   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private LicenceService licenceService;

   private Holder holder;
   private ResponseObjectList<LicenceObject> response;
   private final String authKey = "myPersonalKey";

   @Before
   public void setUp() {
      this.holder = createHolder();
      this.response = populateHolderObject(this.holder);
   }

   @Test
   //code = 200
   public void givenCertifiedAuthentificationKey_whenGetLicencesHolders_thenReturnJson() throws Exception {

      // given
      // given(licenceService.getLicencesHolders(CommissionEnum.CUNCA)).willReturn(this.response);

      // when + then
      //mockMvc
      //      .perform(get("/v1/licences" + "/holders/" + CommissionEnum.CUNCA).header("X-SCC-authentification", authKey)
      //            .contentType(APPLICATION_JSON))
      //      .andExpect(status().isOk()).andExpect(jsonPath("$.id", is(this.holder.getId())))
      //      .andExpect(jsonPath("$.name", is(this.holder.getLastname())));
   }

   @Test
   //code = 401
   public void givenWrongAuthentificationKey_whenGetLicencesHolders_thenReturnJson() throws Exception {

      // given
      // given(licenceService.getLicencesHolders(CommissionEnum.CUNCA)).willReturn(this.response);

      // when + then
      //mockMvc.perform(get("/v1/licences" + "/holders/" + CommissionEnum.CUNCA)
      //      .header("X-SCC-authentification", RandomStringUtils.randomAlphanumeric(20)).contentType(APPLICATION_JSON))
      //      .andExpect(status().isUnauthorized()).andExpect(jsonPath("$.apierror.status", is("UNAUTHORIZED")));

   }

   @Test
   //code = 400
   public void givenWrongTypeId_whenGetLicencesHolders_thenReturnJson() throws Exception {

      // given

      // when + then
      // mockMvc
      //      .perform(get("/v1/licences" + "/holders/" + RandomStringUtils.randomAlphanumeric(8))
      //            .header("X-SCC-authentification", authKey).contentType(APPLICATION_JSON))
      //      .andExpect(status().isBadRequest()).andExpect(jsonPath("$.apierror.status", is("BAD_REQUEST")));

   }

   @Test
   //code = 404
   public void givenHolderNoFound_whenGetLicencesHolders_thenReturnJson() throws Exception {

      // given
      // int id = 123;
      // given(licenceService.getLicencesHolders(CommissionEnum.CUNCA))
      //    .willThrow(new EntityNotFoundException(LicenceObject.class, "id", String.valueOf(id)));

      // when + then
      //mockMvc
      //      .perform(get("/v1/licences" + "/holders/" + CommissionEnum.CUNCA).header("X-SCC-authentification", authKey)
      //            .contentType(APPLICATION_JSON))
      //      .andExpect(status().isNotFound()).andExpect(jsonPath("$.apierror.status", is("NOT_FOUND")));
   }

   private Holder createHolder() {
      Holder holder = new Holder().withId(1).withFirstname("CHARLIE").withLastname("BROWN").withIdCommission(2160);

      return holder;
   }

   private ResponseObjectList<LicenceObject> populateHolderObject(Holder _holder) {

      List<LicenceObject> results = new ArrayList<LicenceObject>();
      results = licenceService.buildResponseObjectLicenceHolder(Arrays.asList(_holder));
      return new ResponseObjectList<LicenceObject>(results.size(), results);
   }

}
