package com.qs.webrtc.stun.model;

import com.qs.webrtc.stun.constants.Stun;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.ByteBuffer;

/**
 * Retransmission TimeOut 推荐500ms,随后*2
 */
@Data
@NoArgsConstructor
public class StunMsg {


    private Header header;

    public static StunMsg parse(ByteBuffer buf) {
        return null;
    }

    /*

       0                   1                   2                   3
       0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
      +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
      |0 0|     STUN Message Type     |         Message Length        |
      +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
      |                         Magic Cookie                          |
      +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
      |                                                               |
      |                     Transaction ID (96 bits)                  |
      |                                                               |
      +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

                  Figure 2: Format of STUN Message Header（20字节）
     */


    /*
                          0                 1
                        2  3  4 5 6 7 8 9 0 1 2 3 4 5

                       +--+--+-+-+-+-+-+-+-+-+-+-+-+-+
                       |M |M |M|M|M|C|M|M|M|C|M|M|M|M|
                       |11|10|9|8|7|1|6|5|4|0|3|2|1|0|
                       +--+--+-+-+-+-+-+-+-+-+-+-+-+-+

                Figure 3: Format of STUN Message Type Field
                C = 0b00 代表这是request请求
                C = 0b01 代表这是indication请求
                C = 0b10 代表这是successResponse响应
                C = 0b11 代表这是errorResponse响应
     */


    //收到请求时做一些检查
    /*
    1.前2位=0
    2.magicCookie是正确的
    3.并且是支持的method
    4.如果响应是SuccessResp or ErrResp,则检查和在处理中的stunreq的transactionId是否匹配。
    5.如果启用了Fingerprint机制，则需要校验Fingeprint,
    如果以上有任何一个不通过，则丢弃该消息（如果和其他协议复用同一socket，则考虑将其解析为其他协议。）
    6.执行身份验证检查。
     */

    /*
    处理请求
    1.如果消息中包含多个未知且需要理解的属性，服务端应该响应420(Unknown Attribute)，并在UNKNOWN-ATTRIBUTES属性中列出未知且需要理解的属性。
     */


    @Data
    public static class Header{
        private Stun.Type type;
        //包含header的20个字节
        private Integer length;
        private Long magicCookie;
        private String transactionId;
    }

}
