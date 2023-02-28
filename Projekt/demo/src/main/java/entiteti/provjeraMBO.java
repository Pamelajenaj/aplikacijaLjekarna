package entiteti;

import exceptioni.MBOMoraImati9Znamenki;
import exceptioni.MBONeSmijeSadrzavatiSlova;

public sealed interface provjeraMBO permits Pacijent{

    void provjeraIspravnostuMBO(String mbo) throws MBOMoraImati9Znamenki, MBONeSmijeSadrzavatiSlova;
}
