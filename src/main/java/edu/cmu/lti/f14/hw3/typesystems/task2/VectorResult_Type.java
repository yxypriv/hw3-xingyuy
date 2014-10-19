
/* First created by JCasGen Sat Oct 18 17:30:07 EDT 2014 */
package edu.cmu.lti.f14.hw3.typesystems.task2;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Sat Oct 18 18:03:42 EDT 2014
 * @generated */
public class VectorResult_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (VectorResult_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = VectorResult_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new VectorResult(addr, VectorResult_Type.this);
  			   VectorResult_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new VectorResult(addr, VectorResult_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = VectorResult.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
 
  /** @generated */
  final Feature casFeat_tokenList;
  /** @generated */
  final int     casFeatCode_tokenList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTokenList(int addr) {
        if (featOkTst && casFeat_tokenList == null)
      jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    return ll_cas.ll_getRefValue(addr, casFeatCode_tokenList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTokenList(int addr, int v) {
        if (featOkTst && casFeat_tokenList == null)
      jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    ll_cas.ll_setRefValue(addr, casFeatCode_tokenList, v);}
    
  
 
  /** @generated */
  final Feature casFeat_stemMethod;
  /** @generated */
  final int     casFeatCode_stemMethod;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getStemMethod(int addr) {
        if (featOkTst && casFeat_stemMethod == null)
      jcas.throwFeatMissing("stemMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    return ll_cas.ll_getStringValue(addr, casFeatCode_stemMethod);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setStemMethod(int addr, String v) {
        if (featOkTst && casFeat_stemMethod == null)
      jcas.throwFeatMissing("stemMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    ll_cas.ll_setStringValue(addr, casFeatCode_stemMethod, v);}
    
  
 
  /** @generated */
  final Feature casFeat_filteredStopword;
  /** @generated */
  final int     casFeatCode_filteredStopword;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getFilteredStopword(int addr) {
        if (featOkTst && casFeat_filteredStopword == null)
      jcas.throwFeatMissing("filteredStopword", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_filteredStopword);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFilteredStopword(int addr, boolean v) {
        if (featOkTst && casFeat_filteredStopword == null)
      jcas.throwFeatMissing("filteredStopword", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_filteredStopword, v);}
    
  
 
  /** @generated */
  final Feature casFeat_allLowerCase;
  /** @generated */
  final int     casFeatCode_allLowerCase;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getAllLowerCase(int addr) {
        if (featOkTst && casFeat_allLowerCase == null)
      jcas.throwFeatMissing("allLowerCase", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_allLowerCase);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setAllLowerCase(int addr, boolean v) {
        if (featOkTst && casFeat_allLowerCase == null)
      jcas.throwFeatMissing("allLowerCase", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_allLowerCase, v);}
    
  
 
  /** @generated */
  final Feature casFeat_tokenizeMethod;
  /** @generated */
  final int     casFeatCode_tokenizeMethod;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getTokenizeMethod(int addr) {
        if (featOkTst && casFeat_tokenizeMethod == null)
      jcas.throwFeatMissing("tokenizeMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    return ll_cas.ll_getStringValue(addr, casFeatCode_tokenizeMethod);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTokenizeMethod(int addr, String v) {
        if (featOkTst && casFeat_tokenizeMethod == null)
      jcas.throwFeatMissing("tokenizeMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    ll_cas.ll_setStringValue(addr, casFeatCode_tokenizeMethod, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public VectorResult_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_tokenList = jcas.getRequiredFeatureDE(casType, "tokenList", "uima.cas.FSList", featOkTst);
    casFeatCode_tokenList  = (null == casFeat_tokenList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tokenList).getCode();

 
    casFeat_stemMethod = jcas.getRequiredFeatureDE(casType, "stemMethod", "uima.cas.String", featOkTst);
    casFeatCode_stemMethod  = (null == casFeat_stemMethod) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_stemMethod).getCode();

 
    casFeat_filteredStopword = jcas.getRequiredFeatureDE(casType, "filteredStopword", "uima.cas.Boolean", featOkTst);
    casFeatCode_filteredStopword  = (null == casFeat_filteredStopword) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_filteredStopword).getCode();

 
    casFeat_allLowerCase = jcas.getRequiredFeatureDE(casType, "allLowerCase", "uima.cas.Boolean", featOkTst);
    casFeatCode_allLowerCase  = (null == casFeat_allLowerCase) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_allLowerCase).getCode();

 
    casFeat_tokenizeMethod = jcas.getRequiredFeatureDE(casType, "tokenizeMethod", "uima.cas.String", featOkTst);
    casFeatCode_tokenizeMethod  = (null == casFeat_tokenizeMethod) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_tokenizeMethod).getCode();

  }
}



    