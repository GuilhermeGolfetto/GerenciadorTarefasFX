package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Tarefa;

public class TarefaDAO {

	public ObservableList<Tarefa> lista() {
		ObservableList<Tarefa> tarefas = FXCollections.observableArrayList();

		PreparedStatement ps;
		Connection conn = null;
		try {
			conn = new ConnectionFactory().getConnection();
			ps = conn.prepareStatement("SELECT * FROM tarefas");

			ResultSet rS = ps.executeQuery();
			while (rS.next()) {
				Tarefa t = new Tarefa();
				t.setNome(rS.getString("nome"));
				t.setDescricao(rS.getString("descricao"));
				t.setPrazo(rS.getString("prazo"));
				t.setPrioridade(rS.getString("prioridade"));
				t.setConcluida(rS.getBoolean("concluida")?"Sim":"Não");

				tarefas.add(t);
			}
			ps.close();
			rS.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return tarefas;
	}

	public void adciona(Tarefa t) {

		PreparedStatement ps;

		Connection conn = null;
		try {
			conn = new ConnectionFactory().getConnection();
			ps = conn.prepareStatement(
					"INSERT INTO tarefas(nome,descricao,prazo,prioridade,concluida) VALUES(?,?,?,?,?);");
			ps.setString(1, t.getNome());
			ps.setString(2, t.getDescricao());
			ps.setString(3, t.getPrazo());
			ps.setString(4, t.getPrioridade());
			ps.setBoolean(5, (t.getConcluida() == "sim")?true:false);
			ps.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally{
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
