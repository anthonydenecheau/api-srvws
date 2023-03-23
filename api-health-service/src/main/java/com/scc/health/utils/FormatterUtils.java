package com.scc.health.utils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class FormatterUtils {

   private static final String BACK_SLASH_N = "\n";
   private static final String AROBASE = "@";
   private static final String DOUBLE_DOT = ":";

   public static String dateToString(Date sDate) {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      return sdf.format(sDate);
   }

   public static String handleStringNullValue(Object value) {
      return (value == null ? "" : (String) value);
   }

   public static Set<Integer> getFirst(Map<DiseaseEnum, Set<Integer>> map) {
      return map.isEmpty() ? Collections.emptySet() : new HashSet<>(map.values().stream().findFirst().get());
   }

   public static String removeErreurPrefix(Exception e) {
      String removePrefixError = StringUtils.EMPTY;
      if (StringUtils.isNotBlank(e.getMessage())) {
         if (e.getMessage().contains("Erreur:")) {
            removePrefixError = e.getMessage().replace("Erreur:", StringUtils.EMPTY);
         } else if (e.getMessage().contains("Erreur :")) {
            removePrefixError = e.getMessage().replace("Erreur :", StringUtils.EMPTY);
         } else if (e.getMessage().contains(" Erreur:")) {
            removePrefixError = e.getMessage().replace(" Erreur:", StringUtils.EMPTY);
         } else if (e.getMessage().contains(" Erreur : ")) {
            removePrefixError = e.getMessage().replace(" Erreur : ", StringUtils.EMPTY);
         } else {
            return e.getMessage();
         }
      }
      return removePrefixError;
   }

   public static String recupererMessageDerreurExceptionOracle(String msg) {
      String msgFormat = "";

      int positionDeuxPointsDebutMessageDerreur;
      int posCharactereRetourChariot;

      try {
         if (StringUtils.isBlank(msg)) {
            return msgFormat;
         }
         msgFormat = msg.replaceAll(BACK_SLASH_N, AROBASE);
         posCharactereRetourChariot = msgFormat.indexOf(AROBASE);
         if (posCharactereRetourChariot > 0) {
            positionDeuxPointsDebutMessageDerreur = msgFormat.indexOf(DOUBLE_DOT);
            msgFormat = msgFormat.substring(positionDeuxPointsDebutMessageDerreur + 1, posCharactereRetourChariot);
         }
      } catch (Exception e) {
         return msgFormat;
      }
      return msgFormat;
   }

   public static String removeOracleCodeError(String msg) {
      String msgFormat = msg;
      final String prefix = "ORA";
      try {
         if (StringUtils.isBlank(msg)) {
            return msgFormat;
         }

         if (msg.contains(prefix)) {
            int indexOfLastdoubleDot = msg.indexOf(DOUBLE_DOT);
            msgFormat = msg.substring(indexOfLastdoubleDot + 1);
         }
      } catch (Exception e) {
         return msgFormat;
      }
      return msgFormat;
   }

   public static String formatMessageErreurOracle(String msg) {
      String msgFormat = "";

      int positionDeuxPointsDebutMessageDerreur;
      int posCharactereRetourChariot;

      try {
         if (StringUtils.isBlank(msg)) {
            return msgFormat;
         }
         msgFormat = msg.replaceAll(BACK_SLASH_N, AROBASE);
         posCharactereRetourChariot = msgFormat.indexOf(AROBASE);
         if (posCharactereRetourChariot > 0) {
            positionDeuxPointsDebutMessageDerreur = msgFormat.indexOf(DOUBLE_DOT);
            msgFormat = msgFormat.substring(positionDeuxPointsDebutMessageDerreur + 1, posCharactereRetourChariot);
         }

         msgFormat = removeCodeError(msgFormat);

      } catch (Exception e) {
         return msgFormat;
      }
      return msgFormat;
   }

   public static String removeCodeError(String msg) {
      String msgFormat = msg;
      final String prefix = ": ORA";
      try {
         if (StringUtils.isBlank(msg)) {
            return msgFormat;
         }

         if (msg.contains(prefix)) {
            int indexOfLastdoubleDot = msg.indexOf(DOUBLE_DOT);
            msgFormat = msg.substring(0, indexOfLastdoubleDot);
         }
      } catch (Exception e) {
         return msgFormat;
      }
      return msgFormat;
   }
}
