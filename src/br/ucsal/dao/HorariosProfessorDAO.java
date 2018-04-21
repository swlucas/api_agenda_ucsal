package br.ucsal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.model.Aluno;
import br.ucsal.model.HorariosProfessor;
import br.ucsal.model.Professor;
import br.ucsal.util.Conexao;

public class HorariosProfessorDAO {
	private Conexao conexao;
	
	public HorariosProfessorDAO() {
		this.conexao = Conexao.getConexao();
	}
	
	public void insert (HorariosProfessor horariosprofessor) throws Exception{
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into agenda_ucsal.horarios_prof (id_prof,id_aluno) values (?,?);");
			ps.setInt(1, horariosprofessor.getProfessor().getId());
			ps.setInt(2, horariosprofessor.getAluno().getId());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<HorariosProfessor> listarTodos() throws Exception{
		Statement stmt;
		List<HorariosProfessor> horariosprofessores = new ArrayList<>();
		
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select id,id_prof,id_aluno from agenda_ucsal.horarios_prof;");
			while(rs.next()) {

				Aluno aluno = new Aluno();
				aluno.setId(rs.getInt("id_aluno"));
				
				Professor professor = new Professor();
				professor.setId(rs.getInt("id_prof"));
				
				HorariosProfessor horariosprofessor = new HorariosProfessor();
				horariosprofessor.setId(rs.getInt("id"));
				horariosprofessor.setProfessor(professor);
				
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return horariosprofessores;
	}
	

	public void update(HorariosProfessor horariosprofessor,int id) throws Exception {
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("update agenda_ucsal.horarios_prof set id_prof =?, id_aluno = ?  where id =?;");
			ps.setInt(1, horariosprofessor.getProfessor().getId());
			ps.setInt(2, horariosprofessor.getAluno().getId());
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
			ps = conexao.getConnection().prepareStatement("delete from agenda_ucsal.horarios_prof where id=?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
}
}
