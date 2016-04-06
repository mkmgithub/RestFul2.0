package org.tempuri;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.3.10
 * 2016-04-05T17:18:15.610+08:00
 * Generated source version: 2.3.10
 * 
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "WebServiceSoap")
//@XmlSeeAlso({ObjectFactory.class})
public interface WebServiceSoap {

    @WebResult(name = "HelloWorldResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "HelloWorld", targetNamespace = "http://tempuri.org/", className = "org.tempuri.HelloWorld")
    @WebMethod(operationName = "HelloWorld", action = "http://tempuri.org/HelloWorld")
    @ResponseWrapper(localName = "HelloWorldResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.HelloWorldResponse")
    public String helloWorld();

    /**
     * 
     */
    @WebResult(name = "UpdateDataResult", targetNamespace = "http://tempuri.org/")
    //@RequestWrapper(localName = "UpdateData", targetNamespace = "http://tempuri.org/", className = "org.tempuri.UpdateData")
    @WebMethod(operationName = "UpdateData", action = "http://tempuri.org/UpdateData")
    //@ResponseWrapper(localName = "UpdateDataResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.UpdateDataResponse")
    public String updateData(
        @WebParam(name = "request", targetNamespace = "http://tempuri.org/")
        String request
    );

    @WebResult(name = "SwapDataResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "SwapData", targetNamespace = "http://tempuri.org/", className = "org.tempuri.SwapData")
    @WebMethod(operationName = "SwapData", action = "http://tempuri.org/SwapData")
    @ResponseWrapper(localName = "SwapDataResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.SwapDataResponse")
    public String swapData(
        @WebParam(name = "methodName", targetNamespace = "http://tempuri.org/")
        String methodName,
        @WebParam(name = "request", targetNamespace = "http://tempuri.org/")
        String request
    );

    @WebResult(name = "SendMessageResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "SendMessage", targetNamespace = "http://tempuri.org/", className = "org.tempuri.SendMessage")
    @WebMethod(operationName = "SendMessage", action = "http://tempuri.org/SendMessage")
    @ResponseWrapper(localName = "SendMessageResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.SendMessageResponse")
    public String sendMessage(
        @WebParam(name = "phone", targetNamespace = "http://tempuri.org/")
        String phone,
        @WebParam(name = "message", targetNamespace = "http://tempuri.org/")
        String message,
        @WebParam(name = "taskname", targetNamespace = "http://tempuri.org/")
        String taskname,
        @WebParam(name = "hospMessagid", targetNamespace = "http://tempuri.org/")
        String hospMessagid
    );
}
