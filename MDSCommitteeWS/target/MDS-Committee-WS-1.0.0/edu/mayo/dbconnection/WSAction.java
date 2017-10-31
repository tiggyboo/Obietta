package edu.mayo.dbconnection;

import edu.mayo.committee.CommitteeData;
import org.json.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.ResultSet;
import java.sql.SQLException;
//import javax.ws.rs.ApplicationPath;
//import javax.ws.rs.core.Application;

//import java.sql.ResultSet;


/**
 * Created by afs01 on 10/9/17.
 */


@Path("committee")
    //@ApplicationPath("/rest")
public class WSAction {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

    //@Path("/list")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    //@Produces({"application/json", "application/xml"})

    public String getData() {

        CommitteeData committeelist = new CommitteeData();
        ResultSet committeeNames = committeelist.buildMyConnection("CMT_LIST",null);
        ResultSetConverter jsonResults = new ResultSetConverter();
        JSONArray myCommitteeArray = null;

        try {
            myCommitteeArray = jsonResults.convert(committeeNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //System.out.print(stuff);
        /*

        ResultSetMetaData rsmd = null;
        int columnCount = 0;

        try {
            rsmd = committeeNames.getMetaData();
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //ArrayList<String> committeeNameList = new ArrayList<String>(columnCount);
        try {

            while (committeeNames.next()) {
                //Retrieve by column name

                //String committeeName = committeeNames.getString("name");

                //System.out.print(committeeName + "\n");
                //------------------------
                /*int i = 1;
                while(i <= columnCount) {
                    committeeNameList.add(committeeNames.getString(i++));
                }
                System.out.print(committeeNameList);
                //-------------------------

            }
        } catch (SQLException e) {
            // do something appropriate with the exception, *at least*:
            e.printStackTrace();
        } */

        return myCommitteeArray.toString();
        //return committeeNames.toString();

    }
    @Path("/id/{committeeID}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getCommitteeById(@PathParam("committeeID") Integer id) {

        CommitteeData committeelist = new CommitteeData();
        ResultSet committeeNames = committeelist.buildMyConnection("CMT_ID",id);
        ResultSetConverter jsonResults = new ResultSetConverter();
        JSONArray myCommitteeArray = null;

        try {
            myCommitteeArray = jsonResults.convert(committeeNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return Response.status(200).entity("getCommitteeById is called, id : " + id).build();
        return myCommitteeArray.toString();
    }
    @Path("/perid/{perid}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPersonByPerid(@PathParam("perid") Integer id) {

        CommitteeData committeelist = new CommitteeData();
        ResultSet committeeNames = committeelist.buildMyConnection("CMT_PERID",id);
        ResultSetConverter jsonResults = new ResultSetConverter();
        JSONArray myCommitteeArray = null;

        try {
            myCommitteeArray = jsonResults.convert(committeeNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return Response.status(200).entity("getCommitteeById is called, id : " + id).build();
        return myCommitteeArray.toString();
    }
    @Path("/members/{committeeID}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMembersByCommitteeID(@PathParam("committeeID") Integer id) {

        CommitteeData committeelist = new CommitteeData();
        ResultSet committeeNames = committeelist.buildMyConnection("CMT_MEM",id);
        ResultSetConverter jsonResults = new ResultSetConverter();
        JSONArray myCommitteeArray = null;

        try {
            myCommitteeArray = jsonResults.convert(committeeNames);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return Response.status(200).entity("getCommitteeById is called, id : " + id).build();
        return myCommitteeArray.toString();
    }

}

