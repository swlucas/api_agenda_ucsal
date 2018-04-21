package br.ucsal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import br.ucsal.model.Aluno;
import br.ucsal.util.Conexao;

public class AlunoDAO {
	private Conexao conexao;
	
	public AlunoDAO() {
		this.conexao = Conexao.getConexao();
	}
	
	public void insert (Aluno aluno) throws Exception{
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into agenda_ucsal.aluno (matricula,nome,email) values (?,?,?);");
			ps.setInt(1,aluno.getMatricula());
			ps.setString(2,aluno.getNome());
			ps.setString(3,aluno.getEmail());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Aluno> listarTodos() throws Exception{
		Statement stmt;
		List<Aluno> alunos = new ArrayList<>();
		
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select id,matricula,nome, email from agenda_ucsal.aluno;");
			while(rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id"));
				aluno.setMatricula(rs.getInt("matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setEmail(rs.getString("email"));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return alunos;
	}
	
	public Aluno getByID(int id) throws Exception {
		Aluno aluno = null;
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("select id,matricula,nome, email from agenda_ucsal.aluno where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				aluno.setId(rs.getInt("id"));
				aluno.setMatricula(rs.getInt("matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aluno;
	}
	
	public void update(Aluno aluno,int id) throws Exception {
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update agenda_ucsal.aluno set matricula =?, nome = ? , email = ? where id =?;");
			ps.setInt(1, aluno.getMatricula());
			ps.setString(2, aluno.getNome());
			ps.setString(3, aluno.getEmail());
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
			ps = conexao.getConnection().prepareStatement("delete from agenda_ucsal.aluno where id=?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
}
}
