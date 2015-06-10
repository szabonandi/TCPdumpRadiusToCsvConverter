/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsnan;

import java.util.Objects;

/**
 *
 * @author szabo1nando612
 */
class RadiusAccountingMessage
  {

    private String tcpDumpTimestamp;

    private String acct_attrib_User_Name;
    private String acct_attrib_NAS_IP_Address;
    private String acct_attrib_Framed_IP_Address;
    private String acct_attrib_NAS_Identifier;
    private String acct_attrib_Acct_Status_Type;
    private String acct_attrib_Acct_Delay_Time;
    private String acct_attrib_Acct_Input_Octets;
    private String acct_attrib_Acct_Output_Octets;
    private String acct_attrib_Acct_Session_Id;
    private String acct_attrib_Acct_Input_Gigawords;
    private String acct_attrib_Acct_Output_Gigawords;
    private String acct_attrib_Event_Timestamp;
    private String acct_attrib_Framed_IP_Netmask;
    private String acct_attrib_Acct_Unique_Session_Id;
    private String acct_attrib_Timestamp;

    @Override
    public String toString()
      {
        return "RadiusAccountingMessage{" + "tcpDumpTimestamp=" + tcpDumpTimestamp + ", acct_attrib_User_Name=" + acct_attrib_User_Name + ", acct_attrib_NAS_IP_Address=" + acct_attrib_NAS_IP_Address + ", acct_attrib_Framed_IP_Address=" + acct_attrib_Framed_IP_Address + ", acct_attrib_NAS_Identifier=" + acct_attrib_NAS_Identifier + ", acct_attrib_Acct_Status_Type=" + acct_attrib_Acct_Status_Type + ", acct_attrib_Acct_Delay_Time=" + acct_attrib_Acct_Delay_Time + ", acct_attrib_Acct_Input_Octets=" + acct_attrib_Acct_Input_Octets + ", acct_attrib_Acct_Output_Octets=" + acct_attrib_Acct_Output_Octets + ", acct_attrib_Acct_Session_Id=" + acct_attrib_Acct_Session_Id + ", acct_attrib_Acct_Input_Gigawords=" + acct_attrib_Acct_Input_Gigawords + ", acct_attrib_Acct_Output_Gigawords=" + acct_attrib_Acct_Output_Gigawords + ", acct_attrib_Event_Timestamp=" + acct_attrib_Event_Timestamp + ", acct_attrib_Framed_IP_Netmask=" + acct_attrib_Framed_IP_Netmask + ", acct_attrib_Acct_Unique_Session_Id=" + acct_attrib_Acct_Unique_Session_Id + ", acct_attrib_Timestamp=" + acct_attrib_Timestamp + '}';
      }

    public String toCsvString()
      {
        return tcpDumpTimestamp + ";" + acct_attrib_User_Name + ";" + acct_attrib_NAS_IP_Address + ";" + acct_attrib_Framed_IP_Address + ";" + acct_attrib_NAS_Identifier + ";" + acct_attrib_Acct_Status_Type + ";" + acct_attrib_Acct_Delay_Time + ";" + acct_attrib_Acct_Input_Octets + ";" + acct_attrib_Acct_Output_Octets + ";" + acct_attrib_Acct_Session_Id + ";" + acct_attrib_Acct_Input_Gigawords + ";" + acct_attrib_Acct_Output_Gigawords + ";" + acct_attrib_Event_Timestamp + ";" + acct_attrib_Framed_IP_Netmask + ";" + acct_attrib_Acct_Unique_Session_Id + ";" + acct_attrib_Timestamp + ";";
      }
    
    public String getCsvHeader()
      {
        return "tcpDumpTimestamp;User_Name;NAS_IP_Address;Framed_IP_Address;NAS_Identifier;Acct_Status_Type;Acct_Delay_Time;Acct_Input_Octets;Acct_Output_Octets;Acct_Session_Id;Acct_Input_Gigawords;Acct_Output_Gigawords;Event_Timestamp;Framed_IP_Netmask;Acct_Unique_Session_Id;Timestamp";
      }
    
    public String getTcpDumpTimestamp()
      {
        return tcpDumpTimestamp;
      }

    public String getAcct_attrib_User_Name()
      {
        return acct_attrib_User_Name;
      }

    public String getAcct_attrib_NAS_IP_Address()
      {
        return acct_attrib_NAS_IP_Address;
      }

    public String getAcct_attrib_Framed_IP_Address()
      {
        return acct_attrib_Framed_IP_Address;
      }

    public String getAcct_attrib_NAS_Identifier()
      {
        return acct_attrib_NAS_Identifier;
      }

    public String getAcct_attrib_Acct_Status_Type()
      {
        return acct_attrib_Acct_Status_Type;
      }

    public String getAcct_attrib_Acct_Delay_Time()
      {
        return acct_attrib_Acct_Delay_Time;
      }

    public String getAcct_attrib_Acct_Input_Octets()
      {
        return acct_attrib_Acct_Input_Octets;
      }

    public String getAcct_attrib_Acct_Output_Octets()
      {
        return acct_attrib_Acct_Output_Octets;
      }

    public String getAcct_attrib_Acct_Session_Id()
      {
        return acct_attrib_Acct_Session_Id;
      }

    public String getAcct_attrib_Acct_Input_Gigawords()
      {
        return acct_attrib_Acct_Input_Gigawords;
      }

    public String getAcct_attrib_Acct_Output_Gigawords()
      {
        return acct_attrib_Acct_Output_Gigawords;
      }

    public String getAcct_attrib_Event_Timestamp()
      {
        return acct_attrib_Event_Timestamp;
      }

    public String getAcct_attrib_Framed_IP_Netmask()
      {
        return acct_attrib_Framed_IP_Netmask;
      }

    public String getAcct_attrib_Acct_Unique_Session_Id()
      {
        return acct_attrib_Acct_Unique_Session_Id;
      }

    public String getAcct_attrib_Timestamp()
      {
        return acct_attrib_Timestamp;
      }

    public void setTcpDumpTimestamp(String tcpDumpTimestamp)
      {
        this.tcpDumpTimestamp = tcpDumpTimestamp;
      }

    public void setAcct_attrib_User_Name(String acct_attrib_User_Name)
      {
        this.acct_attrib_User_Name = acct_attrib_User_Name;
      }

    public void setAcct_attrib_NAS_IP_Address(String acct_attrib_NAS_IP_Address)
      {
        this.acct_attrib_NAS_IP_Address = acct_attrib_NAS_IP_Address;
      }

    public void setAcct_attrib_Framed_IP_Address(String acct_attrib_Framed_IP_Address)
      {
        this.acct_attrib_Framed_IP_Address = acct_attrib_Framed_IP_Address;
      }

    public void setAcct_attrib_NAS_Identifier(String acct_attrib_NAS_Identifier)
      {
        this.acct_attrib_NAS_Identifier = acct_attrib_NAS_Identifier;
      }

    public void setAcct_attrib_Acct_Status_Type(String acct_attrib_Acct_Status_Type)
      {
        this.acct_attrib_Acct_Status_Type = acct_attrib_Acct_Status_Type;
      }

    public void setAcct_attrib_Acct_Delay_Time(String acct_attrib_Acct_Delay_Time)
      {
        this.acct_attrib_Acct_Delay_Time = acct_attrib_Acct_Delay_Time;
      }

    public void setAcct_attrib_Acct_Input_Octets(String acct_attrib_Acct_Input_Octets)
      {
        this.acct_attrib_Acct_Input_Octets = acct_attrib_Acct_Input_Octets;
      }

    public void setAcct_attrib_Acct_Output_Octets(String acct_attrib_Acct_Output_Octets)
      {
        this.acct_attrib_Acct_Output_Octets = acct_attrib_Acct_Output_Octets;
      }

    public void setAcct_attrib_Acct_Session_Id(String acct_attrib_Acct_Session_Id)
      {
        this.acct_attrib_Acct_Session_Id = acct_attrib_Acct_Session_Id;
      }

    public void setAcct_attrib_Acct_Input_Gigawords(String acct_attrib_Acct_Input_Gigawords)
      {
        this.acct_attrib_Acct_Input_Gigawords = acct_attrib_Acct_Input_Gigawords;
      }

    public void setAcct_attrib_Acct_Output_Gigawords(String acct_attrib_Acct_Output_Gigawords)
      {
        this.acct_attrib_Acct_Output_Gigawords = acct_attrib_Acct_Output_Gigawords;
      }

    public void setAcct_attrib_Event_Timestamp(String acct_attrib_Event_Timestamp)
      {
        this.acct_attrib_Event_Timestamp = acct_attrib_Event_Timestamp;
      }

    public void setAcct_attrib_Framed_IP_Netmask(String acct_attrib_Framed_IP_Netmask)
      {
        this.acct_attrib_Framed_IP_Netmask = acct_attrib_Framed_IP_Netmask;
      }

    public void setAcct_attrib_Acct_Unique_Session_Id(String acct_attrib_Acct_Unique_Session_Id)
      {
        this.acct_attrib_Acct_Unique_Session_Id = acct_attrib_Acct_Unique_Session_Id;
      }

    public void setAcct_attrib_Timestamp(String acct_attrib_Timestamp)
      {
        this.acct_attrib_Timestamp = acct_attrib_Timestamp;
      }

    public boolean isEmpty()
      {
        if ((acct_attrib_Acct_Delay_Time == null) && (acct_attrib_Acct_Input_Gigawords == null))
          {
            return true;
          }
        return false;
      }

    @Override
    public int hashCode()
      {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.tcpDumpTimestamp);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_User_Name);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_NAS_IP_Address);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Framed_IP_Address);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_NAS_Identifier);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Acct_Status_Type);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Acct_Delay_Time);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Acct_Input_Octets);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Acct_Output_Octets);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Acct_Session_Id);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Acct_Input_Gigawords);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Acct_Output_Gigawords);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Event_Timestamp);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Framed_IP_Netmask);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Acct_Unique_Session_Id);
        hash = 23 * hash + Objects.hashCode(this.acct_attrib_Timestamp);
        return hash;
      }

  }
