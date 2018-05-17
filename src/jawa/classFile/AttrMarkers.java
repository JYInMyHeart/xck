package jawa.classFile;

public class AttrMarkers implements AttributeInfo{
    @Override
    public void readInfo(ClassReader reader) {

    }

    static class MarkerAttribute extends AttrMarkers{

    }
    static class DeprecatedAttribute extends AttrMarkers{
        private MarkerAttribute markerAttribute;
    }
    static class SyntheticAttribute extends AttrMarkers{
        private MarkerAttribute markerAttribute;
    }
}
