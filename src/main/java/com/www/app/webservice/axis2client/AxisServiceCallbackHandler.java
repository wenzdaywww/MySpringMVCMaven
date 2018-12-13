
/**
 * AxisServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.www.app.webservice.axis2client;

    /**
     *  AxisServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class AxisServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public AxisServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public AxisServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for login method
            * override this method for handling normal response from login operation
            */
           public void receiveResultlogin(
                    com.www.app.webservice.axis2client.AxisServiceStub.LoginResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from login operation
           */
            public void receiveErrorlogin(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for set method
            * override this method for handling normal response from set operation
            */
           public void receiveResultset(
                    com.www.app.webservice.axis2client.AxisServiceStub.SetResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from set operation
           */
            public void receiveErrorset(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getValue method
            * override this method for handling normal response from getValue operation
            */
           public void receiveResultgetValue(
                    com.www.app.webservice.axis2client.AxisServiceStub.GetValueResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getValue operation
           */
            public void receiveErrorgetValue(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUser method
            * override this method for handling normal response from getUser operation
            */
           public void receiveResultgetUser(
                    com.www.app.webservice.axis2client.AxisServiceStub.GetUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUser operation
           */
            public void receiveErrorgetUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get method
            * override this method for handling normal response from get operation
            */
           public void receiveResultget(
                    com.www.app.webservice.axis2client.AxisServiceStub.GetResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get operation
           */
            public void receiveErrorget(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for notify method
            * override this method for handling normal response from notify operation
            */
           public void receiveResultnotify(
                    com.www.app.webservice.axis2client.AxisServiceStub.NotifyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from notify operation
           */
            public void receiveErrornotify(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for logout method
            * override this method for handling normal response from logout operation
            */
           public void receiveResultlogout(
                    com.www.app.webservice.axis2client.AxisServiceStub.LogoutResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from logout operation
           */
            public void receiveErrorlogout(java.lang.Exception e) {
            }
                


    }
    