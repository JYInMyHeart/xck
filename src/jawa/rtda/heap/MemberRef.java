package jawa.rtda.heap;

import jawa.classfiles.constant.ConstantMemberrefInfo;

import java.util.Map;

public class MemberRef extends SymRef {
    String name;
    String descriptor;

    public void copyMemerRefInfo(ConstantMemberrefInfo refInfo) throws Exception {
        className = refInfo.getClassName();
        Map<String, String> map = refInfo.getNameAndType();
        for (Map.Entry<String, String> e : map.entrySet()) {
            name = e.getKey();
            descriptor = e.getValue();
        }
    }
}
