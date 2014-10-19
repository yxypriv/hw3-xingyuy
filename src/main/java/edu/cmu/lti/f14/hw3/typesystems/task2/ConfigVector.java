

/* First created by JCasGen Sun Oct 19 02:07:31 EDT 2014 */
package edu.cmu.lti.f14.hw3.typesystems.task2;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Oct 19 02:07:31 EDT 2014
 * XML source: D:/workspaces/BICProject/hw3-xingyuy/src/main/resources/descriptors/typesystems/VectorSpaceTypes.xml
 * @generated */
public class ConfigVector extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ConfigVector.class);
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
  protected ConfigVector() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ConfigVector(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ConfigVector(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ConfigVector(JCas jcas, int begin, int end) {
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
    if (ConfigVector_Type.featOkTst && ((ConfigVector_Type)jcasType).casFeat_tokenList == null)
      jcasType.jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((ConfigVector_Type)jcasType).casFeatCode_tokenList)));}
    
  /** setter for tokenList - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTokenList(FSList v) {
    if (ConfigVector_Type.featOkTst && ((ConfigVector_Type)jcasType).casFeat_tokenList == null)
      jcasType.jcas.throwFeatMissing("tokenList", "edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector");
    jcasType.ll_cas.ll_setRefValue(addr, ((ConfigVector_Type)jcasType).casFeatCode_tokenList, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: stemMethod

  /** getter for stemMethod - gets 
   * @generated
   * @return value of the feature 
   */
  public String getStemMethod() {
    if (ConfigVector_Type.featOkTst && ((ConfigVector_Type)jcasType).casFeat_stemMethod == null)
      jcasType.jcas.throwFeatMissing("stemMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConfigVector_Type)jcasType).casFeatCode_stemMethod);}
    
  /** setter for stemMethod - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setStemMethod(String v) {
    if (ConfigVector_Type.featOkTst && ((ConfigVector_Type)jcasType).casFeat_stemMethod == null)
      jcasType.jcas.throwFeatMissing("stemMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConfigVector_Type)jcasType).casFeatCode_stemMethod, v);}    
   
    
  //*--------------*
  //* Feature: filteredStopword

  /** getter for filteredStopword - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getFilteredStopword() {
    if (ConfigVector_Type.featOkTst && ((ConfigVector_Type)jcasType).casFeat_filteredStopword == null)
      jcasType.jcas.throwFeatMissing("filteredStopword", "edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((ConfigVector_Type)jcasType).casFeatCode_filteredStopword);}
    
  /** setter for filteredStopword - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFilteredStopword(boolean v) {
    if (ConfigVector_Type.featOkTst && ((ConfigVector_Type)jcasType).casFeat_filteredStopword == null)
      jcasType.jcas.throwFeatMissing("filteredStopword", "edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((ConfigVector_Type)jcasType).casFeatCode_filteredStopword, v);}    
   
    
  //*--------------*
  //* Feature: allLowerCase

  /** getter for allLowerCase - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getAllLowerCase() {
    if (ConfigVector_Type.featOkTst && ((ConfigVector_Type)jcasType).casFeat_allLowerCase == null)
      jcasType.jcas.throwFeatMissing("allLowerCase", "edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((ConfigVector_Type)jcasType).casFeatCode_allLowerCase);}
    
  /** setter for allLowerCase - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setAllLowerCase(boolean v) {
    if (ConfigVector_Type.featOkTst && ((ConfigVector_Type)jcasType).casFeat_allLowerCase == null)
      jcasType.jcas.throwFeatMissing("allLowerCase", "edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((ConfigVector_Type)jcasType).casFeatCode_allLowerCase, v);}    
   
    
  //*--------------*
  //* Feature: tokenizeMethod

  /** getter for tokenizeMethod - gets 
   * @generated
   * @return value of the feature 
   */
  public String getTokenizeMethod() {
    if (ConfigVector_Type.featOkTst && ((ConfigVector_Type)jcasType).casFeat_tokenizeMethod == null)
      jcasType.jcas.throwFeatMissing("tokenizeMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ConfigVector_Type)jcasType).casFeatCode_tokenizeMethod);}
    
  /** setter for tokenizeMethod - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setTokenizeMethod(String v) {
    if (ConfigVector_Type.featOkTst && ((ConfigVector_Type)jcasType).casFeat_tokenizeMethod == null)
      jcasType.jcas.throwFeatMissing("tokenizeMethod", "edu.cmu.lti.f14.hw3.typesystems.task2.ConfigVector");
    jcasType.ll_cas.ll_setStringValue(addr, ((ConfigVector_Type)jcasType).casFeatCode_tokenizeMethod, v);}    
  }

    