package jawa.instructions.base;

/**
 * @author xck
 */
public class ByteCodeReader {
    private byte[] code;
    private int pc;


    public void reset(byte[] code,int pc){
        this.code = code;
        this.pc = pc;
    }

    public int readInt8(){
        return code[pc++];
    }
    public int readInt16(){
        return (readInt8() << 8) | readInt8();

    }
    public int readInt32(){
        return (readInt8() << 24)
                | (readInt8() << 16)
                | (readInt8() << 8)
                | readInt8();
    }
    public int readInt32s(){
        return 0;
    }
    public void skipPadding(){
        
    }
}
