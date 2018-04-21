package br.ucsal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.model.Horarios;
import br.ucsal.util.Conexao;

public class HorariosDAO {
	private Conexao conexao;
	
	public HorariosDAO() {
		this.conexao = Conexao.getConexao();
	}
	
	public void insert (Horarios horario) throws Exception{
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into agenda_ucsal.horarios (hora_inicio,hora_fim) values (?,?,?);");
			ps.setTime(1, Time.valueOf(horario.getHora_inicio()));
			ps.setTime(2, Time.valueOf(horario.getHora_fim()));
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Horarios> listarTodos() throws Exception{
		Statement stmt;
		List<Horarios> horarios = new ArrayList<>();
		
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select id,hora_inicio,hora_fim from agenda_ucsal.horarios;");
			while(rs.next()) {
				Horarios horario = new Horarios();
				horario.setId(rs.getInt("id"));
				horario.setHora_inicio(rs.getTime("hora_inicio").toLocalTime());
				horario.setHora_inicio(rs.getTime("hora_fim").toLocalTime());
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarios;
	}
	
	public Horarios getByID(int id) throws Exception {
		Horarios horarios = null;
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("select  id,hora_inicio,hora_fim from agenda_ucsal.horarios where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Horarios horario = new Horarios();
				horario.setId(rs.getInt("id"));
				horario.setHora_inicio(rs.getTime("hora_inicio").toLocalTime());
				horario.setHora_inicio(rs.getTime("hora_fim").toLocalTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horarios;
	}
	
	public void update(Horarios horario,int id) throws Exception {
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update agenda_ucsal.horarios set hora_inicio =?, hora_fim = ?  where id =?;");
			ps.setTime(1, Time.valueOf(horario.getHora_inicio()));
			ps.setTime(2, Time.valueOf(horario.getHora_fim()));
			ps.setInt(3, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public void delete(int id) throws Exception {
		PreparedStatement ps;
		try {
			ps = conexao.getConnection().prepareStatement("delete from agenda_ucsal.horarios where id=?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
}
}
