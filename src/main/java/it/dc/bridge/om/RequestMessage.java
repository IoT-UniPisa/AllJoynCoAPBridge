package it.dc.bridge.om;

import java.util.HashMap;
import java.util.Map;

import org.alljoyn.bus.annotation.Position;
import org.alljoyn.bus.annotation.Signature;

/**
 * The Class RequestMessage is used in the AllJoyn message exchanges.
 * It represents the CoAP request message and implements the {@link CoAPRequestMessage} interface.
 * 
 * @see CoAPRequestMessage
 * @see Options
 */
public class RequestMessage implements CoAPRequestMessage{

	/** The option fields. */
	@Position(0)
	@Signature("r")
	public Options options;

	/** The query attributes */
	@Position(1)
	@Signature("a{ss}")
	public Map<String,String> attributes;

	/** The message payload. */
	@Position(2)
	@Signature("ay")
	public byte[] payload;

	/**
	 * Instantiates a new request message.
	 */
	public RequestMessage() {

		options = new Options();
		attributes = new HashMap<String, String>();
		// AJ does not allow null value (signature is "ay")
		payload = new byte[]{};

	}

	/**
	 * Instantiates a new request message equal to the specified message parameters.
	 * 
	 * @param options the option set
	 * @param attributes the query attributes
	 * @param payload the message payload
	 */
	public RequestMessage(Options options, Map<String,String> attributes, byte[] payload) {

		this.options = new Options(options);
		this.setAttributes(attributes);
		this.setPayload(payload);

	}

	/* (non-Javadoc)
	 * @see it.dc.bridge.om.CoAPRequestMessage#getOptions()
	 */
	public Options getOptions() {

		return this.options;

	}

	/* (non-Javadoc)
	 * @see it.dc.bridge.om.CoAPRequestMessage#setOptions(it.dc.bridge.om.Options)
	 */
	public void setOptions(Options options) {

		this.options = new Options(options);

	}

	/* (non-Javadoc)
	 * @see it.dc.bridge.om.CoAPRequestMessage#getAttributes()
	 */
	public Map<String, String> getAttributes() {

		return this.attributes;

	}

	/* (non-Javadoc)
	 * @see it.dc.bridge.om.CoAPRequestMessage#setAttributes(java.util.Map)
	 */
	public void setAttributes(Map<String, String> attributes) {

		this.attributes = attributes;

	}

	/* (non-Javadoc)
	 * @see it.dc.bridge.om.CoAPRequestMessage#getPayload()
	 */
	public byte[] getPayload() {

		return this.payload;

	}

	/* (non-Javadoc)
	 * @see it.dc.bridge.om.CoAPRequestMessage#getPayloadString()
	 */
	public String getPayloadString() {

		if(this.payload.length == 0) {
			return "";
		}
		return new String(this.payload, CoAP.UTF8_CHARSET);
	}

	/* (non-Javadoc)
	 * @see it.dc.bridge.om.CoAPRequestMessage#setPayload(byte[])
	 */
	public void setPayload(byte[] payload) {

		if(payload == null) {
			// AJ does not allow null value (signature is "ay")
			this.payload = new byte[]{};
		}else {
			this.payload = payload;
		}

	}

	/* (non-Javadoc)
	 * @see it.dc.bridge.om.CoAPRequestMessage#setPayload(java.lang.String)
	 */
	public void setPayload(String payload) {

		if(payload == null) {
			// AJ does not allow null value (signature is "ay")
			this.payload = new byte[]{};
		} else {
			setPayload(payload.getBytes(CoAP.UTF8_CHARSET));
		}

	}

}