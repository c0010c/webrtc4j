package com.qs.webrtc.stun;


import com.qs.webrtc.stun.constants.Stun;
import com.qs.webrtc.stun.model.StunMsg;

import java.nio.ByteBuffer;
import java.util.Objects;

public class StunMsgHandler {
    public byte[] handle(ByteBuffer byteBuffer) {
        StunMsg stunMsg = StunMsg.parse(byteBuffer);
        if (Objects.isNull(stunMsg)) {
            return null;
        }

        Stun.Type type = stunMsg.getHeader().getType();
        if (type.equals(Stun.Type.REQUEST)) {
            return null;
        } else if (type.equals(Stun.Type.SUCCESS_RESP)) {

        } else if (type.equals(Stun.Type.ERR_RESP)) {

        } else if (type.equals(Stun.Type.INDICATION)) {
            //Needn't to process.
        }

        return null;
    }

    private byte[] handelRequest(StunMsg reqMsg) {
        
        return null;
    }
}
