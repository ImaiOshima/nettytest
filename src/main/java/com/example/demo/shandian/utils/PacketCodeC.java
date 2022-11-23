package com.example.demo.shandian.utils;

import com.example.demo.shandian.domain.*;
import com.example.demo.shandian.inter.Serializer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @Classname PacketCodeC
 * @Description TODO
 * @Author Imai
 * @Date 2022/10/18 15:15
 * @Created by 61635
 */
public class PacketCodeC {
    private static final int MAGIC_NUMBER =  0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private PacketCodeC(){}

    public ByteBuf encode(ByteBufAllocator alloc, Packet packet){
        ByteBuf byteBuf = alloc.ioBuffer();
        byte[] bytes = Serializer.DEFAULT.serializer(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf){
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);

        byte serializerAlgorithm = byteBuf.readByte();

        byte command = byteBuf.readByte();

        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];

        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if(requestType != null && serializer != null){
            return serializer.deserialize(requestType,bytes);
        }
        return null;
    }

    public Class<? extends Packet> getRequestType(byte command){
        if(command == 1){
            return LoginRequestPacket.class;
        }else if (command == 2){
            return LoginResponsePacket.class;
        }else if(command == 3){
            return MessageRequestPacket.class;
        }else{
            return MessageResponsePacket.class;
        }
    }

    public Serializer getSerializer(byte serializerAlgorithm){
        return Serializer.DEFAULT;
    }
}
