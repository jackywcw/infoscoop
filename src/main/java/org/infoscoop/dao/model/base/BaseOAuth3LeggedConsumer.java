package org.infoscoop.dao.model.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the IS_OAUTH_3Legged_CONSUMERS table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="IS_OAUTH_3Legged_CONSUMERS"
 */

public abstract class BaseOAuth3LeggedConsumer  implements Serializable {

	public static String REF = "OAuth3LeggedConsumer";
	public static String PROP_SERVICE_NAME = "ServiceName";
	public static String PROP_SIGNATURE_METHOD = "SignatureMethod";
	public static String PROP_GADGET_URL_KEY = "GadgetUrlKey";
	public static String PROP_GADGET_URL = "GadgetUrl";
	public static String PROP_CONSUMER_SECRET = "ConsumerSecret";
	public static String PROP_ID = "Id";
	public static String PROP_IS_UPLOAD = "IsUpload";
	public static String PROP_CONSUMER_KEY = "ConsumerKey";


	// constructors
	public BaseOAuth3LeggedConsumer () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseOAuth3LeggedConsumer (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseOAuth3LeggedConsumer (
		java.lang.Long id,
		java.lang.String gadgetUrl,
		java.lang.String gadgetUrlKey,
		java.lang.String serviceName,
		java.lang.Integer isUpload) {

		this.setId(id);
		this.setGadgetUrl(gadgetUrl);
		this.setGadgetUrlKey(gadgetUrlKey);
		this.setServiceName(serviceName);
		this.setIsUpload(isUpload);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String gadgetUrl;
	private java.lang.String gadgetUrlKey;
	private java.lang.String serviceName;
	private java.lang.String consumerKey;
	private java.lang.String consumerSecret;
	private java.lang.String signatureMethod;
	private java.lang.Integer isUpload;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="ID"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: gadget_url
	 */
	public java.lang.String getGadgetUrl () {
		return gadgetUrl;
	}

	/**
	 * Set the value related to the column: gadget_url
	 * @param gadgetUrl the gadget_url value
	 */
	public void setGadgetUrl (java.lang.String gadgetUrl) {
		this.gadgetUrl = gadgetUrl;
	}



	/**
	 * Return the value associated with the column: gadget_url_key
	 */
	public java.lang.String getGadgetUrlKey () {
		return gadgetUrlKey;
	}

	/**
	 * Set the value related to the column: gadget_url_key
	 * @param gadgetUrlKey the gadget_url_key value
	 */
	public void setGadgetUrlKey (java.lang.String gadgetUrlKey) {
		this.gadgetUrlKey = gadgetUrlKey;
	}



	/**
	 * Return the value associated with the column: service_name
	 */
	public java.lang.String getServiceName () {
		return serviceName;
	}

	/**
	 * Set the value related to the column: service_name
	 * @param serviceName the service_name value
	 */
	public void setServiceName (java.lang.String serviceName) {
		this.serviceName = serviceName;
	}



	/**
	 * Return the value associated with the column: consumer_key
	 */
	public java.lang.String getConsumerKey () {
		return consumerKey;
	}

	/**
	 * Set the value related to the column: consumer_key
	 * @param consumerKey the consumer_key value
	 */
	public void setConsumerKey (java.lang.String consumerKey) {
		this.consumerKey = consumerKey;
	}



	/**
	 * Return the value associated with the column: consumer_secret
	 */
	public java.lang.String getConsumerSecret () {
		return consumerSecret;
	}

	/**
	 * Set the value related to the column: consumer_secret
	 * @param consumerSecret the consumer_secret value
	 */
	public void setConsumerSecret (java.lang.String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}



	/**
	 * Return the value associated with the column: signature_method
	 */
	public java.lang.String getSignatureMethod () {
		return signatureMethod;
	}

	/**
	 * Set the value related to the column: signature_method
	 * @param signatureMethod the signature_method value
	 */
	public void setSignatureMethod (java.lang.String signatureMethod) {
		this.signatureMethod = signatureMethod;
	}



	/**
	 * Return the value associated with the column: is_upload
	 */
	public java.lang.Integer getIsUpload () {
		return isUpload;
	}

	/**
	 * Set the value related to the column: is_upload
	 * @param isUpload the is_upload value
	 */
	public void setIsUpload (java.lang.Integer isUpload) {
		this.isUpload = isUpload;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof org.infoscoop.dao.model.OAuth3LeggedConsumer)) return false;
		else {
			org.infoscoop.dao.model.OAuth3LeggedConsumer oAuth3LeggedConsumer = (org.infoscoop.dao.model.OAuth3LeggedConsumer) obj;
			if (null == this.getId() || null == oAuth3LeggedConsumer.getId()) return false;
			else return (this.getId().equals(oAuth3LeggedConsumer.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}