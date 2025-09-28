package main.java.com.agentpay.utils;

public class SQLQueries {
    public static String selectAll(String tableName) {
        return "SELECT * FROM " + tableName;
    }

    public static String selectByField(String tableName, String fieldName) {
        return "SELECT * FROM " + tableName + " WHERE " + fieldName + " = ?";
    }

    public static String deleteById(String tableName, String idField) {
        return "DELETE FROM " + tableName + " WHERE " + idField + " = ?";
    }

    public static String insertInto(String tableName, String... columns) {
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + "(");
        query.append(String.join(", ", columns));
        query.append(") VALUES (");
        query.append("?, ".repeat(columns.length - 1)).append("?)");
        return query.toString();
    }

    public static String updateQuery(String tableName, String idField, String... columns) {
        StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");
        for (int i = 0; i < columns.length; i++) {
            query.append(columns[i]).append("=?");
            if (i < columns.length - 1)
                query.append(", ");
        }
        query.append(" WHERE ").append(idField).append("=?");
        return query.toString();
    }

    public static String selectAllDepartements() {
        return "SELECT " +
                "d.departementID AS departementID, " + 
                "d.name AS nom_departement, " +
                "r.firstName AS prenom_responsable, " +
                "r.lastName AS nom_responsable, " +
                "COUNT(a.agentID) AS total_agents " +
                "FROM departements d " +
                "JOIN agents r ON r.departementID = d.departementID AND r.type_agent = 'RESPONSABLE' " +
                "JOIN agents a ON a.departementID = d.departementID " +
                "GROUP BY d.departementID, r.firstName, r.lastName;";
    }

}
