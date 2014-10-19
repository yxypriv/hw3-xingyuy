
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
 * Updated by JCasGen Sun Oct 19 02:07:30 EDT 2014
 * @generated */
public class Task2Document_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Task2Document_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Task2Document_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Task2Document(addr, Task2Document_Type.this);
  			   Task2Document_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Task2Document(addr, Task2Document_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Task2Document.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
 
  /** @generated */
  final Feature casFeat_rel;
  /** @generated */
  final int     casFeatCode_rel;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getRel(int addr) {
        if (featOkTst && casFeat_rel == null)
      jcas.throwFeatMissing("rel", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    return ll_cas.ll_getIntValue(addr, casFeatCode_rel);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setRel(int addr, int v) {
        if (featOkTst && casFeat_rel == null)
      jcas.throwFeatMissing("rel", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    ll_cas.ll_setIntValue(addr, casFeatCode_rel, v);}
    
  
 
  /** @generated */
  final Feature casFeat_qid;
  /** @generated */
  final int     casFeatCode_qid;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getQid(int addr) {
        if (featOkTst && casFeat_qid == null)
      jcas.throwFeatMissing("qid", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    return ll_cas.ll_getIntValue(addr, casFeatCode_qid);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQid(int addr, int v) {
        if (featOkTst && casFeat_qid == null)
      jcas.throwFeatMissing("qid", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    ll_cas.ll_setIntValue(addr, casFeatCode_qid, v);}
    
  
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_vectorList;
  /** @generated */
  final int     casFeatCode_vectorList;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getVectorList(int addr) {
        if (featOkTst && casFeat_vectorList == null)
      jcas.throwFeatMissing("vectorList", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    return ll_cas.ll_getRefValue(addr, casFeatCode_vectorList);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setVectorList(int addr, int v) {
        if (featOkTst && casFeat_vectorList == null)
      jcas.throwFeatMissing("vectorList", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    ll_cas.ll_setRefValue(addr, casFeatCode_vectorList, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Task2Document_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_rel = jcas.getRequiredFeatureDE(casType, "rel", "uima.cas.Integer", featOkTst);
    casFeatCode_rel  = (null == casFeat_rel) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_rel).getCode();

 
    casFeat_qid = jcas.getRequiredFeatureDE(casType, "qid", "uima.cas.Integer", featOkTst);
    casFeatCode_qid  = (null == casFeat_qid) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_qid).getCode();

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_vectorList = jcas.getRequiredFeatureDE(casType, "vectorList", "uima.cas.FSList", featOkTst);
    casFeatCode_vectorList  = (null == casFeat_vectorList) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_vectorList).getCode();

  }
}



    