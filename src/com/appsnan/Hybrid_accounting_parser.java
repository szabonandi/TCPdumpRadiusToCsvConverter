/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsnan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author szabo1nando612
 */
public class Hybrid_accounting_parser
  {

    final static String dateFormatToFind = "E MMM d HH:mm:ss yyyy";

    static String errorText;
    static FileReader fr;

    private static String removeStartAndEndQuotationMarks(String valueString)
      {
        if (valueString.startsWith("\""))
          {
            if (valueString.endsWith("\""))
              {
                valueString = valueString.substring(1, valueString.length() - 1);
              }
          }
        return valueString;
      }

    private enum lineProcessingStateEnum
      {

        INITIAL, INMESSAGE
      };

    static private lineProcessingStateEnum lineProcessingState = lineProcessingStateEnum.INITIAL;

    static private RadiusAccountingMessage actualRadiusAccountingMessage;
    static private ArrayList<RadiusAccountingMessage> radiusAccountingMessageList = new ArrayList<>();

    static private SimpleDateFormat formatter;

    static private int emptyRadiusAccountingMessageHashCode;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
      {
        if (args.length == 0)
          {
            System.out.println("Usage:");
            System.out.println("First command line parameter: radius tcp dump file ASCII");
            System.exit(1);
          }

        String inputfilename = args[0];

        formatter = new SimpleDateFormat(dateFormatToFind, new Locale("english"));

//        String datum = formatter.format(new Date());
//        System.out.println("Trying to parse String: " + datum);
//
//        try
//          {
//            //Date d = formatter.parse(datum);
//            Date d = formatter.parse("Mon Jun  8 14:43:47 2015");
//            
//            System.out.println("Sikeres parsolas!");
//          } catch (ParseException ex)
//          {
//            Logger.getLogger(Hybrid_accounting_parser.class.getName()).log(Level.SEVERE, null, ex);
//          }
        System.out.println("Processing...");
        System.out.println("input file: " + inputfilename);

        try
          {
            fr = new FileReader(inputfilename);
          } catch (FileNotFoundException ex)
          {
            errorText = "Input file opening failed";
            Logger.getLogger(Hybrid_accounting_parser.class.getName()).log(Level.SEVERE, errorText, ex);
            System.exit(1);
          }

        BufferedReader textReader = new BufferedReader(fr);

        System.out.println("\nParsing input file...");
        String line;
        try
          {
            while ((line = textReader.readLine()) != null)
              {
                processLine(line);
              }
            if (actualRadiusAccountingMessage.hashCode() != emptyRadiusAccountingMessageHashCode)
              {
                radiusAccountingMessageList.add(actualRadiusAccountingMessage);
                System.out.println(actualRadiusAccountingMessage.toString());
              }
            System.out.println("\nParsing completed");

          } catch (IOException ex)
          {
            errorText = "Input file reading failed";
            Logger.getLogger(Hybrid_accounting_parser.class.getName()).log(Level.SEVERE, errorText, ex);
            System.exit(1);
          }

        System.out.println("\nCSV export...\n");
        System.out.println(actualRadiusAccountingMessage.getCsvHeader());

        for (RadiusAccountingMessage ram : radiusAccountingMessageList)
          {
            System.out.println(ram.toCsvString());
          }
      }

    private static void processLine(String line)
      {

//Tue Jun  9 00:03:48 2015
//	User-Name = "taki_dsl_p"
//	NAS-IP-Address = 192.168.53.11
//	Framed-IP-Address = 78.92.255.12
//	NAS-Identifier = "MT_HAAP_INFOG"
//	Acct-Status-Type = Interim-Update
//	Acct-Delay-Time = 0
//	Acct-Input-Octets = 410536
//	Acct-Output-Octets = 1117830
//	Acct-Session-Id = "MT_HAAP030000000000000d6cf6283648"
//	Acct-Input-Gigawords = 0
//	Acct-Output-Gigawords = 0
//	Event-Timestamp = "Jun  9 2015 00:03:47 CEST"
//	Framed-IP-Netmask = 255.255.255.255
//	Acct-Unique-Session-Id = "3a48eb713547a3be"
//	Timestamp = 1433801028
//
//Tue Jun  9 00:08:19 2015
//	User-Name = "elemerA016_dsl"
//	NAS-IP-Address = 192.168.53.11
//	Framed-IP-Address = 78.92.255.8
//	NAS-Identifier = "MT_HAAP_INFOG"
//	Acct-Status-Type = Interim-Update
//	Acct-Delay-Time = 0
//	Acct-Input-Octets = 83790
//	Acct-Output-Octets = 101750
//	Acct-Session-Id = "MT_HAAP020000000000001db25e283653"
//	Acct-Input-Gigawords = 0
//	Acct-Output-Gigawords = 0
//	Event-Timestamp = "Jun  9 2015 00:08:19 CEST"
//	Framed-IP-Netmask = 255.255.255.255
//	Acct-Unique-Session-Id = "a4a559c8c5d03422"
//	Timestamp = 1433801299
        String startstring;

        //System.out.println(line);
        switch (lineProcessingState)
          {
            case INITIAL: //we are not in radius message, initial state
                //Tue Jun  9 00:03:48 2015

                try
                  {
                    Date d = formatter.parse(line);
                  } catch (ParseException ex)
                  {
                    System.out.println("not date: " + line);
                    break;
                  }

                actualRadiusAccountingMessage = new RadiusAccountingMessage();

                emptyRadiusAccountingMessageHashCode = actualRadiusAccountingMessage.hashCode();

                actualRadiusAccountingMessage.setTcpDumpTimestamp(line);
                System.out.println("TcpDumpTimestamp: " + line);
                //System.out.println(actualRadiusAccountingMessage.toString());

                lineProcessingState = lineProcessingStateEnum.INMESSAGE;
                break;

            case INMESSAGE: //we are in attribute definition section
                //	User-Name = "taki_dsl_p"
                startstring = "	User-Name = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_User_Name(valueString);
                    System.out.println("User-Name: " + valueString);
                    break;
                  }

                //	NAS-IP-Address = 192.168.53.11
                startstring = "	NAS-IP-Address = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_NAS_IP_Address(valueString);
                    System.out.println("NAS-IP-Address: " + valueString);
                    break;
                  }

                //	Framed-IP-Address = 78.92.255.12
                startstring = "	Framed-IP-Address = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Framed_IP_Address(valueString);
                    System.out.println("Framed-IP-Address: " + valueString);
                    break;
                  }

                //	NAS-Identifier = "MT_HAAP_INFOG"
                startstring = "	NAS-Identifier = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_NAS_Identifier(valueString);
                    System.out.println("NAS-Identifier: " + valueString);
                    break;
                  }

                //	Acct-Status-Type = Interim-Update
                startstring = "	Acct-Status-Type = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Acct_Status_Type(valueString);
                    System.out.println("Acct-Status-Type: " + valueString);
                    break;
                  }

                //	Acct-Delay-Time = 0
                startstring = "	Acct-Delay-Time = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Acct_Delay_Time(valueString);
                    System.out.println("Acct-Delay-Time: " + valueString);
                    break;
                  }

                //	Acct-Input-Octets = 410536
                startstring = "	Acct-Input-Octets = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Acct_Input_Octets(valueString);
                    System.out.println("Acct-Input-Octets: " + valueString);
                    break;
                  }

                //	Acct-Output-Octets = 1117830
                startstring = "	Acct-Output-Octets = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Acct_Output_Octets(valueString);
                    System.out.println("Acct-Output-Octets: " + valueString);
                    break;
                  }

                //	Acct-Session-Id = "MT_HAAP030000000000000d6cf6283648"
                startstring = "	Acct-Session-Id = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Acct_Session_Id(valueString);
                    System.out.println("Acct-Session-Id: " + valueString);
                    break;
                  }

                //	Acct-Input-Gigawords = 0
                startstring = "	Acct-Input-Gigawords = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Acct_Input_Gigawords(valueString);
                    System.out.println("Acct-Input-Gigawords: " + valueString);
                    break;
                  }

                //	Acct-Output-Gigawords = 0
                startstring = "	Acct-Output-Gigawords = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Acct_Output_Gigawords(valueString);
                    System.out.println("Acct-Output-Gigawords: " + valueString);
                    break;
                  }

                //	Event-Timestamp = "Jun  9 2015 00:03:47 CEST"
                startstring = "	Event-Timestamp = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Event_Timestamp(valueString);
                    System.out.println("Event-Timestamp: " + valueString);
                    break;
                  }

                //	Framed-IP-Netmask = 255.255.255.255
                startstring = "	Framed-IP-Netmask = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Framed_IP_Netmask(valueString);
                    System.out.println("Framed-IP-Netmask: " + valueString);
                    break;
                  }

                //	Acct-Unique-Session-Id = "a4a559c8c5d03422"
                startstring = "	Acct-Unique-Session-Id = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Acct_Unique_Session_Id(valueString);
                    System.out.println("Acct-Unique-Session-Id: " + valueString);
                    break;
                  }

                //	Timestamp = 1433801028
                startstring = "	Timestamp = ";
                if (line.startsWith(startstring))
                  {
                    String valueString = line.substring(startstring.length());
                    valueString = removeStartAndEndQuotationMarks(valueString);
                    actualRadiusAccountingMessage.setAcct_attrib_Timestamp(valueString);
                    System.out.println("Timestamp: " + valueString);
                    break;
                  }

                //Tue Jun  9 00:03:48 2015
                try
                  {
                    Date d = formatter.parse(line);
                    radiusAccountingMessageList.add(actualRadiusAccountingMessage);
                    System.out.println(actualRadiusAccountingMessage.toString() + "\n");

                    actualRadiusAccountingMessage = new RadiusAccountingMessage();
                    actualRadiusAccountingMessage.setTcpDumpTimestamp(line);
                  } catch (ParseException ex)
                  {
                    System.out.println("    unprocessed line: " + line);
                    break;
                  }
          }
      }

  }
