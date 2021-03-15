package ifrn.projeto.casamento.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifrn.projeto.casamento.models.Casamento;
import ifrn.projeto.casamento.models.Empresa;
import ifrn.projeto.casamento.repositories.EmpresaRepository;

@Controller
@RequestMapping("/site")
public class EmpresaController {
	
	@Autowired
	EmpresaRepository er;
	
	@GetMapping("/formEmpresa")
	public String formEmpresa(Empresa empresa) {
		return "/site/formEmpresa";
	}
	
	@PostMapping("formEmpresa")
	public String salvarEmpresa(@Valid Empresa empresa, BindingResult result, RedirectAttributes atributos) {
		if(result.hasErrors()) {
			return formEmpresa(empresa);
		}
		er.save(empresa);
		atributos.addFlashAttribute("mensagem", "empresa cadastrada com sucesso!");
		return "redirect:/site/formEmpresa";
	}
	
	@GetMapping("/listarEmpresas")
	public ModelAndView listarEmpresas() {
		List<Empresa> empresas = er.findAll();
		ModelAndView mv = new ModelAndView("site/listarEmpresas");
		mv.addObject("empresas", empresas);
		return mv;
	}
	
	@GetMapping("/detalhar/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		Optional<Empresa> opt = er.findById(id);
		
		if(opt.isEmpty()) {
			mv.setViewName("redirect:/site/listarEmpresas");
			return mv;
		}
		mv.setViewName("site/detalhesEmpresa");
		Empresa empresa = opt.get();
		
		
		mv.addObject("empresa", empresa);

		
		return mv;
	}
	
	@GetMapping("/{id}/selecionarEmpresa")
	public ModelAndView selecionarEmpresa(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		Optional<Empresa> opt = er.findById(id);
		if (opt.isEmpty()) {
			mv.setViewName("redirect:/site");
			return mv;
		}

		Empresa empresa = opt.get();
		mv.setViewName("site/formEmpresa");
		mv.addObject("empresa", empresa);

		return mv;
	}
	
	@GetMapping("/{id}/removerEmpresa")
	public String removerEmpresa(@PathVariable Long id, RedirectAttributes atributos) {
		Optional<Empresa> opt = er.findById(id);
		
		if(!opt.isEmpty()) {
			Empresa empresa = opt.get();
			er.delete(empresa);
			atributos.addFlashAttribute("mensagem", "empresa removida com sucesso!");
		}
		return "redirect:/site/listarEmpresas";
	}
}
