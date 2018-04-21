package br.ucsal.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.ucsal.dao.AgendamentoDAO;
import br.ucsal.model.Agendamento;



@Path("/agendamento")
public class AgendamentoService {
	
	private AgendamentoDAO agendamentoDAO;
	
	@PostConstruct
	private void init() {
		agendamentoDAO = new AgendamentoDAO();
	}
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String adicionarAgendamento(Agendamento agendamento) {
		String msg = "";
		
		try {
			agendamentoDAO.insert(agendamento);
			msg = "Foi solicitado a "+agendamento.getProfessor()+" um agendamento. ";
		} catch (Exception e) {
			msg = "Erro ao tentar solicitar agendamento";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Agendamento> listarAgendamento(){
		
		List<Agendamento> agendamentos = null;
		try {
			agendamentos = agendamentoDAO.listar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agendamentos;
	}
	
	@GET
	@Path("/list/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Agendamento listarAgendamentoId(@PathParam("id") int id){
		Agendamento agendamento = null;
		try {
			agendamento = agendamentoDAO.getByID(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agendamento;
	}

	
		@PUT
		@Path("/edit/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String editarAgendamento(Agendamento agendamento,@PathParam("id") int id) {
			String msg = "";
			
			try {
				agendamentoDAO.update(agendamento,id);
				
				msg = "Editado com Sucesso!";
			} catch (Exception e) {
				msg = "Erro ao tentar fazer a Edição";
				e.printStackTrace();
			}
			
			return msg;
		}
		
		
		@DELETE
		@Path("/delete/{id}")
		@Produces(MediaType.TEXT_PLAIN)
		public String removerAgendamento(@PathParam("id") int id) {
			String msg = "";
			
			try {
				agendamentoDAO.delete(id);
				
				msg = "Remoção feita com Sucesso!";
			} catch (Exception e) {
				msg = "Erro ao tentar fazer a Remoção";
				e.printStackTrace();
			}
			
			return msg;
		}

}
