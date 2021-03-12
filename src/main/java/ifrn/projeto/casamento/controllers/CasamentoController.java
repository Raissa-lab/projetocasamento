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
import ifrn.projeto.casamento.repositories.CasamentoRepository;
import ifrn.projeto.casamento.repositories.EmpresaRepository;

@Controller
@RequestMapping("/site")
public class CasamentoController {
	@Autowired
	private CasamentoRepository cr;
	
	@Autowired
	private EmpresaRepository er;

	@GetMapping("/formCasamento")
	public String formCasamento(Casamento casamento) {
		return "/site/formCasamento";
	}

	@PostMapping("listarCasamentos")
	public String salvar(@Valid Casamento casamento, BindingResult result, RedirectAttributes atributos) {
		if(result.hasErrors()) {
			return formCasamento(casamento);
		}
		cr.save(casamento);
		atributos.addFlashAttribute("mensagem", "casamento cadastrado com sucesso!");
		return "redirect:/site/listarCasamentos";
	}
	
	@GetMapping("/listarCasamentos")
	public ModelAndView listarCasamentos() {
		List<Casamento> casamentos = cr.findAll();
		ModelAndView mv = new ModelAndView("site/listarCasamentos");
		mv.addObject("casamentos", casamentos);
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		Optional<Casamento> opt = cr.findById(id);
		
		if(opt.isEmpty()) {
			mv.setViewName("redirect:/site/listarCasamentos");
			return mv;
		}
		mv.setViewName("site/detalhes");
		Casamento casamento = opt.get();
		mv.addObject("casamento", casamento);
		
		return mv;
	}
	
	@GetMapping("/{id}/remover")
	public String removerCasamento(@PathVariable Long id, RedirectAttributes atributos) {
		Optional<Casamento> opt = cr.findById(id);
		
		if(!opt.isEmpty()) {
			Casamento casamento = opt.get();
			cr.delete(casamento);
			atributos.addFlashAttribute("mensagem", "casamento removido com sucesso!");
		}
		return "redirect:/site/listarCasamentos";
	}
	
}
