package nl.svendubbeld.inject;

public class ParameterDataObject extends DataObject {

    DerivedDataObject mDerivedDataObject;

    public ParameterDataObject(DerivedDataObject derivedDataObject) {
        mDerivedDataObject = derivedDataObject;
    }

    public DerivedDataObject getDerivedDataObject() {
        return mDerivedDataObject;
    }
}
