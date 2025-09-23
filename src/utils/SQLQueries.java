package utils;

public class SQLQueries {
    public static final String FIND_AGENT_BY_EMAIL =
        "SELECT * FROM agent WHERE email = ?";

    public static final String FIND_AGENT_BY_ID =
        "SELECT * FROM agent WHERE id_agent = ?";

    public static final String INSERT_AGENT =
        "INSERT INTO agent(nom, prenom, email, mot_de_passe, type_agent) VALUES (?, ?, ?, ?, ?)";

    public static final String UPDATE_AGENT =
        "UPDATE agent SET nom=?, prenom=?, email=?, mot_de_passe=?, type_agent=? WHERE id_agent=?";
    
    public static final String DELETE_AGENT =
        "DELETE FROM agent WHERE id_agent=?";
}


