package br.ucsal.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.model.Agendamento;
import br.ucsal.model.Aluno;
import br.ucsal.model.Horarios;
import br.ucsal.model.Professor;
import br.ucsal.util.Conexao;

public class AgendamentoDAO {
	private Conexao conexao;
	
	public AgendamentoDAO() {
		this.conexao = Conexao.getConexao();
	}
	
	public void insert (Agendamento agendamento) throws Exception{
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into agenda_ucsal.agendamento (assunto,descricao,data,hora_agendada,id_prof,id_aluno) \r\n" + 
							"values\r\n" + 
							"(?,?,?,?,?,?);");
			
			ps.setString(1,agendamento.getAssunto());
			ps.setString(2,agendamento.getDescricao());
			ps.setDate(3, Date.valueOf(agendamento.getData()));
			ps.setInt(4, agendamento.getHorarios().getId());
			ps.setInt(5,agendamento.getProfessor().getId());
			ps.setInt(6, agendamento.getAluno().getId());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Agendamento> listar() throws Exception{
		Statement stmt;
		List<Agendamento> agendamentos = new ArrayList<>();
		
		try {
			stmt = conexao.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select AGENDA.id, AGENDA.assunto ,AGENDA.descricao,	AGENDA.data ,	HORARIOS.hora_inicio,	HORARIOS.hora_fim,	PROF.nome_prof,	ALUNO.nome_aluno from agenda_ucsal.agendamento AGENDA inner join agenda_ucsal.horarios HORARIOS on HORARIOS.id = AGENDA.hora_agendada	inner join agenda_ucsal.professor PROF on PROF.id = AGENDA.id_prof	inner join agenda_ucsal.aluno ALUNO on ALUNO.id = AGENDA.id_aluno");
			while(rs.next()) {
				
				Horarios horarios = new Horarios();
				horarios.setHora_inicio(rs.getTime("hora_inicio").toLocalTime());
				horarios.setHora_fim(rs.getTime("hora_fim").toLocalTime());
				
				Professor professor = new Professor();
		//		professor.setMatricula(rs.getInt("matricula_prof"));
				professor.setEmail(rs.getString("nome_prof"));
//				professor.setEmail(rs.getString("email_prof"));
				
				
				Aluno aluno = new Aluno();
//				aluno.setMatricula(rs.getInt("matricula_aluno"));
				aluno.setEmail(rs.getString("nome_aluno"));
//				aluno.setEmail(rs.getString("email_aluno"));
				
				Agendamento agendamento = new Agendamento();
				agendamento.setId(rs.getInt("id"));
				agendamento.setAssunto(rs.getString("assunto"));
				agendamento.setDescricao(rs.getString("descricao"));
				agendamento.setData(rs.getDate("data").toLocalDate());
				agendamento.setHorarios(horarios);
				agendamento.setProfessor(professor);
				agendamento.setAluno(aluno);
				agendamentos.add(agendamento);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agendamentos;
	}
	
	public Agendamento getByID(int id) throws Exception {
		Agendamento agendamento = null;
		try {
			PreparedStatement ps = conexao.getConnection().prepareStatement("select AGENDA.id, AGENDA.assunto ,AGENDA.descricao,	AGENDA.data ,	HORARIOS.hora_inicio,	HORARIOS.hora_fim,	PROF.nome_prof,	ALUNO.nome_aluno from agenda_ucsal.agendamento AGENDA inner join agenda_ucsal.horarios HORARIOS on HORARIOS.id = AGENDA.hora_agendada	inner join agenda_ucsal.professor PROF on PROF.id = AGENDA.id_prof	inner join agenda_ucsal.aluno ALUNO on ALUNO.id = AGENDA.id_aluno where AGENDA.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Horarios horarios = new Horarios();
				horarios.setHora_inicio(rs.getTime("hora_inicio").toLocalTime());
				horarios.setHora_inicio(rs.getTime("hora_inicio").toLocalTime());
				
				Professor professor = new Professor();
				//professor.setMatricula(rs.getInt("matricula_prof"));
				professor.setEmail(rs.getString("nome_prof"));
				//professor.setEmail(rs.getString("email_prof"));
				
				
				Aluno aluno = new Aluno();
			//	aluno.setMatricula(rs.getInt("matricula_aluno"));
				aluno.setEmail(rs.getString("nome_aluno"));
			//	aluno.setEmail(rs.getString("email_aluno"));
				agendamento  = new Agendamento();
				//agendamento.setId(rs.getInt("id"));
				agendamento.setAssunto(rs.getString("assunto"));
				agendamento.setDescricao(rs.getString("descricao"));
				agendamento.setData(rs.getDate("data").toLocalDate());
				agendamento.setHorarios(horarios);
				agendamento.setProfessor(professor);
				agendamento.setAluno(aluno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return agendamento;
	}
	
	public void update(Agendamento agendamento,int id) throws Exception {
		try {
			PreparedStatement ps = conexao.getConnection()
					.prepareStatement("insert into agenda_ucsal.agendamento (assunto,descricao,data,hora_agendada,id_prof,id_aluno) \r\n" + 
							"values\r\n" + 
							"(?,?,?,?,?,?) where id = ?;");
			
			ps.setString(1,agendamento.getAssunto());
			ps.setString(2,agendamento.getDescricao());
			ps.setDate(3, Date.valueOf(agendamento.getData()));
			ps.setInt(4, agendamento.getHorarios().getId());
			ps.setInt(5,agendamento.getProfessor().getId());
			ps.setInt(6, agendamento.getAluno().getId());
			ps.setInt(7, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public void delete(int id) throws Exception {
		PreparedStatement ps;
		try {
			ps = conexao.getConnection().prepareStatement("delete from agenda_ucsal.agendamento where id=?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
}
}
