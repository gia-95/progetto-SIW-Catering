package it.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.siw.catering.model.Buffet;
import it.siw.catering.repository.BuffetRepository;

@Service
public class BuffetService {

	@Autowired
	private BuffetRepository buffetRepository;

	public List<Buffet> getBuffets() {

		List<Buffet> buffets = new ArrayList<>();

		for (Buffet buffet : this.buffetRepository.findAll()) {
			buffets.add(buffet);
		}

		return buffets;
	}

	public boolean addBuffet(Buffet buffet) {

		Buffet buffetSaved = this.buffetRepository.save(buffet);
		return buffetSaved != null;
	}

}
