package edu.mayo.committee;

import edu.mayo.dbconnection.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import org.apache.commons.dbutils.DbUtils;


/**
 * Created by afs01 on 9/25/17.
 */


public class CommitteeData {

    public  ResultSet buildMyConnection(String whichQuery, Integer id) {
    //public List<String> buildMyConnection() {

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    String sql = null;
        /*if (whichQuery == "CMT_LIST") {
            sql = "select name from committee where end_date >= getdate() and name !='' and committee_type_code != 'CT+Minutes' order by name";
        } else {
            sql = "select ai,name,end_date from committee where name like 'P%'";
        }*/
try {

    sql = getSQL(whichQuery, id);

    con = ConnectionManager.getConnection();

    try {
        stmt = con.createStatement();
    } catch (SQLException e) {
        // do something appropriate with the exception, *at least*:
        e.printStackTrace();
    }
    try {
        rs = stmt.executeQuery(sql);
    } catch (SQLException e) {
        // do something appropriate with the exception, *at least*:
        e.printStackTrace();
    }
     /*finally {
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(stmt);
        DbUtils.closeQuietly(con);
    }*/
} catch (Exception e){
    e.printStackTrace();
        }
        finally {
    try {
    con.close();
        }
        catch (Exception e) {
        e.printStackTrace();
        }

        }
        return rs;

    }
    private String getSQL(String whichQuery, Integer id) {
        String theQuery;

        switch (whichQuery) {
            case "CMT_LIST":
                theQuery = "select name from committee where end_date >= getdate() and name !='' and committee_type_code != 'CT+Minutes' order by name";
                break;
            case "CMT_ID":
                theQuery = "select * from committee where ai = " + id;
                break;
            case "CMT_PERID":
                theQuery = "select t1.name,isnull(t4.long_name,'Member') as member_role from committee t1, committee_member t2, person t3, member_role t4 where t1.ai = t2.committee_ai and t3.ai = t2.person_ai and t4.code = t2.member_role_code and t3.per_id = " + id + "order by t1.name";
                break;
            case "CMT_MEM":
                theQuery = "select t1.last_name, t1.first_name, t1.middle_name, isnull(t3.long_name,'Member') as member_role from person t1, committee_member t2, member_role t3 where t1.ai = t2.person_ai and t3.code = t2.member_role_code and t2.committee_ai = " +  id + " order by t1.last_name, t1.first_name";
                break;
            default:
                throw new IllegalArgumentException("Invalid query specified!");
        }
        return theQuery;
    }
        }
    //}

//}


//@Path("committeelist")

//@GET

//@Produces(MediaType.TEXT_PLAIN)

//public String getIt() {

//return

