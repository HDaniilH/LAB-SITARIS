package test.files.xmlmarshalling;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum QueryType {
    @XmlEnumValue("CREATE")
    CREATE,
    @XmlEnumValue("READ")
    READ,
    @XmlEnumValue("UPDATE")
    UPDATE,
    @XmlEnumValue("DELETE")
    DELETE;
}

