package com.dhcc.pms.ws.his.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2014-08-07T11:45:39.302+08:00
 * Generated source version: 2.7.11
 * 
 */
@WebServiceClient(name = "SCM", 
                  wsdlLocation = "http://10.160.17.11:57772/dthealth/web/web.DHCST.SoapService.SCMDataExchange.CLS?WSDL=1",
                  targetNamespace = "http://www.dhcc.com") 
public class SCM extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.dhcc.com", "SCM");
    public final static QName SCMSoap = new QName("http://www.dhcc.com", "SCMSoap");
    static {
        URL url = null;
        try {
            url = new URL("http://10.160.17.11:57772/dthealth/web/web.DHCST.SoapService.SCMDataExchange.CLS?WSDL=1");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SCM.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.160.17.11:57772/dthealth/web/web.DHCST.SoapService.SCMDataExchange.CLS?WSDL=1");
        }
        WSDL_LOCATION = url;
    }

    public SCM(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SCM(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SCM() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SCM(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SCM(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SCM(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns SCMSoap
     */
    @WebEndpoint(name = "SCMSoap")
    public SCMSoap getSCMSoap() {
        return super.getPort(SCMSoap, SCMSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SCMSoap
     */
    @WebEndpoint(name = "SCMSoap")
    public SCMSoap getSCMSoap(WebServiceFeature... features) {
        return super.getPort(SCMSoap, SCMSoap.class, features);
    }

}