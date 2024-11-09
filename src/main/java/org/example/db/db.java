package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class db {
    String url = "jdbc:postgresql://localhost:5432/acceso_datos";

    String user = "postgres";

    String password = "marcos";

    public db() {
        this.createTables();
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    private void createTables() {
        Connection conn = this.connect();
        String sql = "CREATE TABLE IF NOT EXISTS contratos (\n"
                + " id SERIAL PRIMARY KEY,\n"
                + " adjudicatario VARCHAR(255) NOT NULL,\n"
                + " objeto_generico VARCHAR(255) NOT NULL,\n"
                + " fecha_adjudicacion VARCHAR(255) NOT NULL,\n"
                + " importe DOUBLE PRECISION NOT NULL,\n"
                + " proveedores_consultados INTEGER\n"  // Changed from NUMBER to INTEGER
                + ");";

        try {
            conn.createStatement().execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Contrato getAllContratos() {
        Connection conn = this.connect();
        String sql = "SELECT * FROM contratos";
        Contrato contrato = null;
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                contrato = new Contrato(rs.getString("adjudicatario"), rs.getString("objeto_generico"), rs.getString("fecha_adjudicacion"), rs.getDouble("importe"), rs.getInt("proveedores_consultados"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contrato;
    }

    public void saveContrato(Contrato contrato) {
        Connection conn = this.connect();
        String sql = "INSERT INTO contratos(adjudicatario, objeto_generico, fecha_adjudicacion, importe, proveedores_consultados) VALUES ('" + contrato.getAdjudicatario() + "', '" + contrato.getObjeto_generico() + "', '" + contrato.getFecha_adjudicacion() + "', " + contrato.getImporte() + ", " + contrato.getProveedores_consultados() + ")";
        try {
            conn.createStatement().execute(sql);
            System.out.println("Contrato saved successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
