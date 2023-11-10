package com.qs.webrtc.stun.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface Stun {

    Long MAGIC_COOKIE = 0x2112A442L;

    @Getter
    @AllArgsConstructor
    enum Type{
        REQUEST(0x0001),
        SUCCESS_RESP(0x0101),
        /*
         Note: This unfortunate encoding is due to assignment of values in
      [RFC3489] that did not consider encoding Indications, Success, and
      Errors using bit fields.
         */
        ERR_RESP(0),
        INDICATION(0),

        ;
        private final Integer val;
    }
}
