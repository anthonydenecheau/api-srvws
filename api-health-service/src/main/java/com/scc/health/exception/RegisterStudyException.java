package com.scc.health.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

public class RegisterStudyException extends RuntimeException {

   private static final long serialVersionUID = 1L;

   public RegisterStudyException(Class clazz, String... searchParamsMap) {
      super(RegisterStudyException.generateMessage(clazz.getSimpleName(),
            toMap(String.class, String.class, searchParamsMap)));
   }

   private static String generateMessage(String entity, Map<String, String> searchParams) {
      return StringUtils.capitalize(entity) + " has encountered an error " + searchParams;
   }

   private static <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
      if (entries.length % 2 == 1)
         throw new IllegalArgumentException("Invalid entries");
      return IntStream.range(0, entries.length / 2).map(i -> i * 2).collect(HashMap::new,
            (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])), Map::putAll);
   }

}