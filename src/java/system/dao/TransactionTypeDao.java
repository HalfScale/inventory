package system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import system.bean.TransactionType;

public class TransactionTypeDao {

    private static final String SQL_CREATE_TRANSACTION_TYPE = "insert into `transaction_type` (`name`) VALUES (?)";
    private static final String SQL_GET_TRANSACTION_TYPE_BY_ID = "select * from `transaction_type` where id = ?";
    private static final String SQL_GET_ALL_TRANSACTION_TYPE = "select * from `transaction_type`";
    private static final String SQL_UPDATE_TRANSACTION_TYPE = "update `transaction_type` set `name` = ? where id = ?";
    private static final String SQL_DELETE_TRANSACTION_TYPE = "delete from `transaction_type` where id = ?";

    public static void create(Connection con, TransactionType transactionType) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_TRANSACTION_TYPE, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, transactionType.getName());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    transactionType.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static TransactionType getById(Connection con, int id) throws SQLException {
        TransactionType transactionType = new TransactionType();
        ResultSet rs = null;
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_TRANSACTION_TYPE_BY_ID)) {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                transactionType.setId(rs.getInt(1));
                transactionType.setName(rs.getString(2));
            }
        }

        return transactionType;
    }

    public static List<TransactionType> getAll(Connection con) throws SQLException {
        List<TransactionType> transactionTypes = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_TRANSACTION_TYPE);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                TransactionType transactionType = new TransactionType();
                transactionType.setId(rs.getInt(1));
                transactionType.setName(rs.getString(2));
                transactionTypes.add(transactionType);
            }
        }

        return transactionTypes;
    }

    public static void update(Connection con, TransactionType transactionType) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_TRANSACTION_TYPE)) {
            pstmt.setString(1, transactionType.getName());
            pstmt.setInt(2, transactionType.getId());
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection con, int id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_TRANSACTION_TYPE)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
