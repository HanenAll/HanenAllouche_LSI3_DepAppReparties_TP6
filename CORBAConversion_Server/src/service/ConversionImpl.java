package service;

import corbaConversion.IConversionRemotePOA;

// c'est la classe qui doit heriter du skeleton c'est la classe IconversionRemotePOA
public class ConversionImpl extends IConversionRemotePOA {
    @Override
    //implementation de la methode de conversion de montant
    public double conversionMontant(double mt) {
        return mt*3.3;
    }
}
