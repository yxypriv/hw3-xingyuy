

/* First created by JCasGen Sat Oct 18 17:30:07 EDT 2014 */
package edu.cmu.lti.f14.hw3.typesystems.task2;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Oct 18 18:03:42 EDT 2014
 * XML source: D:/workspaces/BICProject/hw3-xingyuy/src/main/resources/descriptors/typesystems/VectorSpaceTypes.xml
 * @generated */
public class VectorResult extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(VectorResult.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected VectorResult() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public VectorResult(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public VectorResult(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public VectorResult(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: tokenList

  /** getter for tokenList - gets 
   * @generated
   * @return value of the feature 
   */
  public FSList getTokenList() {
    if (VectorResult_Type.featOkTst && ((VectorResult_Type)jcasType).casFeat_tokenList == null)
      jcasType.jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((VectorResult_Type)jcasType).casFeatCode_tokenList)));}
    
  /** setter for tokenList - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTokenList(FSList v) {
    if (VectorResult_Type.featOkTst && ((VectorResult_Type)jcasType).casFeat_tokenList == null)
      jcasType.jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    jcasType.ll_cas.ll_setRefValue(addr, ((VectorResult_Type)jcasType).casFeatCode_tokenList, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: stemMethod

  /** getter for stemMethod - gets 
   * @generated
   * @return value of the feature 
   */
  public String getStemMethod() {
    if (VectorResult_Type.featOkTst && ((VectorResult_Type)jcasType).casFeat_stemMethod == null)
      jcasType.jcas.throwFeatMissing("stemMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    return jcasType.ll_cas.ll_getStringValue(addr, ((VectorResult_Type)jcasType).casFeatCode_stemMethod);}
    
  /** setter for stemMethod - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setStemMethod(String v) {
    if (VectorResult_Type.featOkTst && ((VectorResult_Type)jcasType).casFeat_stemMethod == null)
      jcasType.jcas.throwFeatMissing("stemMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    jcasType.ll_cas.ll_setStringValue(addr, ((VectorResult_Type)jcasType).casFeatCode_stemMethod, v);}    
   
    
  //*--------------*
  //* Feature: filteredStopword

  /** getter for filteredStopword - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getFilteredStopword() {
    if (VectorResult_Type.featOkTst && ((VectorResult_Type)jcasType).casFeat_filteredStopword == null)
      jcasType.jcas.throwFeatMissing("filteredStopword", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((VectorResult_Type)jcasType).casFeatCode_filteredStopword);}
    
  /** setter for filteredStopword - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFilteredStopword(boolean v) {
    if (VectorResult_Type.featOkTst && ((VectorResult_Type)jcasType).casFeat_filteredStopword == null)
      jcasType.jcas.throwFeatMissing("filteredStopword", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((VectorResult_Type)jcasType).casFeatCode_filteredStopword, v);}    
   
    
  //*--------------*
  //* Feature: allLowerCase

  /** getter for allLowerCase - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getAllLowerCase() {
    if (VectorResult_Type.featOkTst && ((VectorResult_Type)jcasType).casFeat_allLowerCase == null)
      jcasType.jcas.throwFeatMissing("allLowerCase", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((VectorResult_Type)jcasType).casFeatCode_allLowerCase);}
    
  /** setter for allLowerCase - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAllLowerCase(boolean v) {
    if (VectorResult_Type.featOkTst && ((VectorResult_Type)jcasType).casFeat_allLowerCase == null)
      jcasType.jcas.throwFeatMissing("allLowerCase", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((VectorResult_Type)jcasType).casFeatCode_allLowerCase, v);}    
   
    
  //*--------------*
  //* Feature: tokenizeMethod

  /** getter for tokenizeMethod - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTokenizeMethod() {
    if (VectorResult_Type.featOkTst && ((VectorResult_Type)jcasType).casFeat_tokenizeMethod == null)
      jcasType.jcas.throwFeatMissing("tokenizeMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    return jcasType.ll_cas.ll_getStringValue(addr, ((VectorResult_Type)jcasType).casFeatCode_tokenizeMethod);}
    
  /** setter for tokenizeMethod - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTokenizeMethod(String v) {
    if (VectorResult_Type.featOkTst && ((VectorResult_Type)jcasType).casFeat_tokenizeMethod == null)
      jcasType.jcas.throwFeatMissing("tokenizeMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.VectorResult");
    jcasType.ll_cas.ll_setStringValue(addr, ((VectorResult_Type)jcasType).casFeatCode_tokenizeMethod, v);}    
  }

    