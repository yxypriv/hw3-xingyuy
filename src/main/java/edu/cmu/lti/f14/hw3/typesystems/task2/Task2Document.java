

/* First created by JCasGen Sat Oct 18 17:30:07 EDT 2014 */
package edu.cmu.lti.f14.hw3.typesystems.task2;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sun Oct 19 02:07:30 EDT 2014
 * XML source: D:/workspaces/BICProject/hw3-xingyuy/src/main/resources/descriptors/typesystems/VectorSpaceTypes.xml
 * @generated */
public class Task2Document extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Task2Document.class);
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
  protected Task2Document() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Task2Document(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Task2Document(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Task2Document(JCas jcas, int begin, int end) {
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
  //* Feature: rel

  /** getter for rel - gets 
   * @generated
   * @return value of the feature 
   */
  public int getRel() {
    if (Task2Document_Type.featOkTst && ((Task2Document_Type)jcasType).casFeat_rel == null)
      jcasType.jcas.throwFeatMissing("rel", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Task2Document_Type)jcasType).casFeatCode_rel);}
    
  /** setter for rel - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setRel(int v) {
    if (Task2Document_Type.featOkTst && ((Task2Document_Type)jcasType).casFeat_rel == null)
      jcasType.jcas.throwFeatMissing("rel", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    jcasType.ll_cas.ll_setIntValue(addr, ((Task2Document_Type)jcasType).casFeatCode_rel, v);}    
   
    
  //*--------------*
  //* Feature: qid

  /** getter for qid - gets 
   * @generated
   * @return value of the feature 
   */
  public int getQid() {
    if (Task2Document_Type.featOkTst && ((Task2Document_Type)jcasType).casFeat_qid == null)
      jcasType.jcas.throwFeatMissing("qid", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Task2Document_Type)jcasType).casFeatCode_qid);}
    
  /** setter for qid - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQid(int v) {
    if (Task2Document_Type.featOkTst && ((Task2Document_Type)jcasType).casFeat_qid == null)
      jcasType.jcas.throwFeatMissing("qid", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    jcasType.ll_cas.ll_setIntValue(addr, ((Task2Document_Type)jcasType).casFeatCode_qid, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (Task2Document_Type.featOkTst && ((Task2Document_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Task2Document_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (Task2Document_Type.featOkTst && ((Task2Document_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    jcasType.ll_cas.ll_setStringValue(addr, ((Task2Document_Type)jcasType).casFeatCode_text, v);}    
   
    
  //*--------------*
  //* Feature: vectorList

  /** getter for vectorList - gets 
   * @generated
   * @return value of the feature 
   */
  public FSList getVectorList() {
    if (Task2Document_Type.featOkTst && ((Task2Document_Type)jcasType).casFeat_vectorList == null)
      jcasType.jcas.throwFeatMissing("vectorList", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    return (FSList)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Task2Document_Type)jcasType).casFeatCode_vectorList)));}
    
  /** setter for vectorList - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setVectorList(FSList v) {
    if (Task2Document_Type.featOkTst && ((Task2Document_Type)jcasType).casFeat_vectorList == null)
      jcasType.jcas.throwFeatMissing("vectorList", "edu.cmu.lti.f14.hw3.typesystems.task2.Task2Document");
    jcasType.ll_cas.ll_setRefValue(addr, ((Task2Document_Type)jcasType).casFeatCode_vectorList, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    