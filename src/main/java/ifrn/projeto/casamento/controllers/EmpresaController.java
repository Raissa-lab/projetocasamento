package ifrn.projeto.casamento.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.projeto.casamento.models.Empresa;
import ifrn.projeto.casamento.repositories.EmpresaRepository;

@Controller
@RequestMapping("/site")
public class EmpresaController {
	
	@Autowired
	EmpresaRepository er;
	
	@GetMapping("/formEmpresa")
	public String formEmpresa() {
		return "/site/formEmpresa";
	}
	

	@PostMapping("index")
	public String salvarEmpresa(Empresa empresa) {
		er.save(empresa);
		return "/site/index";
	}
}
