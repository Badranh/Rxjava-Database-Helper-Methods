package net.cryptobrewery.rxdatabaseexample.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Callable;

import static net.cryptobrewery.rxdatabaseexample.Database.DBConnector.getConn;


public  abstract  class SQLQueryExecuter  implements Callable<ResultSet>{
    private String query;


    public abstract void failure(Exception ex);


    SQLQueryExecuter(String query) {

        this.query = query;
    }

    @Override
    public ResultSet call()  {
        try {
            Connection con = getConn();
            if (con == null) {
                failure(new Exception("Connection Error"));
            }
            else {
                Statement stmnt = con.createStatement();
                ResultSet rs =  stmnt.executeQuery(query);

                if(rs!=null)
                    return rs;
                else
                failure(new Exception("error reading from database"));
            }
        }
        catch (final Exception ex)
        {
            failure(ex);
        }
        return null;
    }
}

