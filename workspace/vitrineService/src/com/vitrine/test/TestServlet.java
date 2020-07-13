package com.vitrine.test;

import java.io.IOException;
import java.sql.Date;

import javax.annotation.Resource;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.vitrine.entities.Trace;
import com.vitrine.entities.Usuario;
import com.vitrine.entities.dao.jdbc.EntityManagerDAO;

/**
 * Servlet implementation class TesteServlet
 */
@WebServlet(loadOnStartup=1, urlPatterns ="/TesteServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource
	private DataSource dataSource;
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		getDataSource();
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		EntityManagerDAO dao = new EntityManagerDAO(getDataSource());
		
		Usuario u = new Usuario();
		u.setIdUsuario("newTest");
		u.setSenha("12345");
		Trace t = new Trace();
		t.setDataAlteracao(new Date(99999999));
		t.setDataInclusao(new Date(99999999));
		t.setSituacao("s");
		u.setTrace(t);
		
		dao.executeCreate(u);
		
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getDataSource();
		doGet(request, response);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
