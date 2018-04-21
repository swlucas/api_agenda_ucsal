package br.ucsal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import br.ucsal.model.Professor;
import br.ucsal.util.Conexao;

public class ProfessorDAO {
	private Conexao conexao;
	
	public ProfessorDAO() {
		this.conexao = Conexao.getConexao();
	}
	
	public void insert (Professor professor) throws Exception{
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into agenda_ucsal.professor (matricula,nome,email) values (?,?,?);");
			ps.setInt(1, professor.getMatricula());
			ps.setString(2, professor.getNome());
			ps.setString(3, professor.getEmail());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Professor> listarTodos() throws Exception{
		Statement stmt;
		List<Professor> professores = new ArrayList<>();
		
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select id,matricula,nome, email from agenda_ucsal.professor;");
			while(rs.next()) {
				Professor professor = new Professor();
				professor.setId(rs.getInt("id"));
				professor.setMatricula(rs.getInt("matricula"));
				professor.setNome(rs.getString("nome"));
				professor.setEmail(rs.getString("email"));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professores;
	}
	
	public Professor getByID(int id) throws Exception {
		Professor professor = null;
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("select id,matricula,nome, email from agenda_ucsal.professor where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				professor.setId(rs.getInt("id"));
				professor.setMatricula(rs.getInt("matricula"));
				professor.setNome(rs.getString("nome"));
				professor.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return professor;
	}
	
	public void update(Professor professor,int id) throws Exception {
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update agenda_ucsal.professor set matricula =?, nome = ? , email = ? where id =?;");
			ps.setInt(1, professor.getMatricula());
			ps.setString(2, professor.getNome());
			ps.setString(3, professor.getEmail());
			ps.setInt(4, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public void delete(int id) throws Exception {
		PreparedStatement ps;
		try {
			ps = conexao.getConnection().prepareStatement("delete from agenda_ucsal.professor where id=?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
}
}
